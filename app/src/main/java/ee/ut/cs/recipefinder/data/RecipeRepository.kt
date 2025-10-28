package ee.ut.cs.recipefinder.data

import ee.ut.cs.recipefinder.data.local.dao.RecipeDao
import ee.ut.cs.recipefinder.data.local.mappers.toDomain
import ee.ut.cs.recipefinder.data.local.mappers.toEntity
import ee.ut.cs.recipefinder.domain.model.Recipe

class RecipeRepository(private val dao: RecipeDao) {

    suspend fun getAllRecipes(): List<Recipe> =
        dao.getAll().map { it.toDomain() }

    suspend fun getRecipeById(id: String): Recipe? =
        dao.getById(id)?.toDomain()

    suspend fun saveRecipe(recipe: Recipe) {
        dao.insert(recipe.toEntity())
    }

    suspend fun clearAll() = dao.clearAll()
}
