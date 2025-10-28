package ee.ut.cs.recipefinder.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Test

class MealDbApiTest {

    private val api = MealDbRetrofit.api

    @Test
    fun testFetchRecipes() = runBlocking {
        val query = "chicken"
        val response = api.searchMeals(query)

        // Print results to the console (for manual testing)
        println("Fetched meals: ${response.meals?.size}")
        response.meals?.forEach { meal ->
            println("Meal: ${meal.strMeal}, id: ${meal.idMeal}")
        }

        // Assertions
        assert(response.meals?.isNotEmpty() == true)
    }
}
