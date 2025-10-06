package ee.ut.cs.recipefinder.domain.model

data class Ingredient(
    val name: String,
    val quantity: Double? = null,
    val unit: String? = null,
)
