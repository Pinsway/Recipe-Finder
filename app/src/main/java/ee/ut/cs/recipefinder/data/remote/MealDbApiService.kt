package ee.ut.cs.recipefinder.data.remote

import ee.ut.cs.recipefinder.data.remote.model.MealDbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbApiService {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealDbResponse
}
