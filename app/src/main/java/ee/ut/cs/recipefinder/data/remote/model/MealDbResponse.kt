package ee.ut.cs.recipefinder.data.remote.model

data class MealDbResponse(
    val meals: List<MealDto>?
)

data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strTags: String?,
    val strSource: String?,
    // TheMealDB API supports up to 20 ingredients and measures
    val strIngredient1: String?, val strIngredient2: String?, val strIngredient3: String?,
    val strIngredient4: String?, val strIngredient5: String?, val strIngredient6: String?,
    val strIngredient7: String?, val strIngredient8: String?, val strIngredient9: String?,
    val strIngredient10: String?, val strIngredient11: String?, val strIngredient12: String?,
    val strIngredient13: String?, val strIngredient14: String?, val strIngredient15: String?,
    val strIngredient16: String?, val strIngredient17: String?, val strIngredient18: String?,
    val strIngredient19: String?, val strIngredient20: String?,
    val strMeasure1: String?, val strMeasure2: String?, val strMeasure3: String?,
    val strMeasure4: String?, val strMeasure5: String?, val strMeasure6: String?,
    val strMeasure7: String?, val strMeasure8: String?, val strMeasure9: String?,
    val strMeasure10: String?, val strMeasure11: String?, val strMeasure12: String?,
    val strMeasure13: String?, val strMeasure14: String?, val strMeasure15: String?,
    val strMeasure16: String?, val strMeasure17: String?, val strMeasure18: String?,
    val strMeasure19: String?, val strMeasure20: String?
) {
    fun toDomain(): ee.ut.cs.recipefinder.domain.model.Recipe {
        val ingredients = mutableListOf<ee.ut.cs.recipefinder.domain.model.Ingredient>()

        val names = listOf(
            strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
        )
        val measures = listOf(
            strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
            strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
            strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
            strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
        )

        names.zip(measures).forEach { (name, qty) ->
            if (!name.isNullOrBlank()) {
                // The measure is in the form of a string like "4" or "200g" or "to taste" or "a pinch" for example.
                val quantity = qty?.replace("[^\\d.]".toRegex(), "")?.toDoubleOrNull() // can be null
                val unit = qty?.replace("[\\d.]".toRegex(), "")?.trim() // will probably be an arbitrary unit like "to taste" if quantity is null
                ingredients.add(ee.ut.cs.recipefinder.domain.model.Ingredient(name, quantity, unit))
            }
        }

        return ee.ut.cs.recipefinder.domain.model.Recipe(
            id = idMeal,
            title = strMeal,
            description = null,
            ingredients = ingredients,
            instructions = strInstructions,
            imageUrl = strMealThumb,
            cookTimeMinutes = null,
            servings = null,
            tags = strTags?.split(",") ?: emptyList(),
            sourceUrl = strSource
        )
    }
}

