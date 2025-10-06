package ee.ut.cs.recipefinder.domain.model

import java.util.UUID

data class Recipe(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: String? = null,
    val imageUrl: String? = null,
    val cookTimeMinutes: Int? = null,
    val servings: Int? = null,
    val tags: List<String> = emptyList(),
    val sourceUrl: String? = null,
)
