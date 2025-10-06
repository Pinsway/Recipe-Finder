package ee.ut.cs.recipefinder.domain.model

data class RecipeSearchFilter(
    val includeIngredients: List<String> = emptyList(),
    val excludeIngredients: List<String> = emptyList(),
    val maxCookTimeMinutes: Int? = null,
    val tags: List<String> = emptyList()
)
