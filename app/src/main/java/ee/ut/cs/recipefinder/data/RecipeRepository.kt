package ee.ut.cs.recipefinder.data

import ee.ut.cs.recipefinder.data.local.dao.RecipeDao
import ee.ut.cs.recipefinder.data.local.mappers.toDomain
import ee.ut.cs.recipefinder.data.local.mappers.toEntity
import ee.ut.cs.recipefinder.data.remote.MealDbRetrofit.api
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.domain.util.Resource

class RecipeRepository(private val dao: RecipeDao) {

    suspend fun getAllRecipes(): List<Recipe> =
        dao.getAll().map { it.toDomain() }

    suspend fun getRecipeById(id: String): Recipe? =
        dao.getById(id)?.toDomain()

    suspend fun saveRecipe(recipe: Recipe) {
        dao.insert(recipe.toEntity())
    }

    suspend fun clearAll() = dao.clearAll()

    /**
     * Fetches recipes from TheMealDb API based on search query.
     * @param query the recipe name or keyword to search for, use this like you are searching keywords manually from a large set of data.
     * @return list of Recipes (wrapped in Resource for error handling).
     */
    suspend fun fetchRecipesFromApi(query: String): Resource<List<Recipe>> {
        return try {
            val response = api.searchMeals(query)
            val recipes = response.meals?.map { it.toDomain() } ?: emptyList()
            // Save to Room
            recipes.forEach { saveRecipe(it) }
            Resource.Success(recipes)
        } catch (e: Exception) {
            // Handle no internet or parsing errors
            Resource.Error(
                message = "Failed to fetch recipes. Check your internet connection.",
                throwable = e
            )
        }
    }

    /**
     * Fetches recipes by a list of first letters (a..z) to broaden results.
     */
    suspend fun fetchRecipesByFirstLetters(letters: List<Char>): Resource<List<Recipe>> {
        return try {
            val all = mutableListOf<Recipe>()
            for (ch in letters) {
                val response = api.searchMealsByFirstLetter(ch.toString())
                val items = response.meals?.map { it.toDomain() } ?: emptyList()
                items.forEach { saveRecipe(it) }
                all += items
            }
            // Deduplicate by id
            val unique = all.distinctBy { it.id }
            Resource.Success(unique)
        } catch (e: Exception) {
            Resource.Error(
                message = "Failed to fetch more recipes.",
                throwable = e
            )
        }
    }
}
