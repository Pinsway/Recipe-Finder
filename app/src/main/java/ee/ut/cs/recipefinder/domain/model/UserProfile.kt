package ee.ut.cs.recipefinder.domain.model

data class UserProfile(
    val id: String,
    val name: String? = null,
    val email: String? = null,
    val favoriteRecipeIds: List<String> = emptyList(),
    val preferredIngredients: List<String> = emptyList(),
    val excludedIngredients: List<String> = emptyList()
)
