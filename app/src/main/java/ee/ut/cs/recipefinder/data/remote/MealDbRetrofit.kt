package ee.ut.cs.recipefinder.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealDbRetrofit {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: MealDbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDbApiService::class.java)
    }
}
