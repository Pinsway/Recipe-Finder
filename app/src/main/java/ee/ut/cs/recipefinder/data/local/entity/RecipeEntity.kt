package ee.ut.cs.recipefinder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ee.ut.cs.recipefinder.data.local.converters.*
import ee.ut.cs.recipefinder.domain.model.Ingredient

@Entity(tableName = "recipes")
@TypeConverters(IngredientListConverter::class, StringListConverter::class)
data class RecipeEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val ingredients: List<Ingredient>,
    val instructions: String?,
    val imageUrl: String?,
    val cookTimeMinutes: Int?,
    val servings: Int?,
    val tags: List<String>,
    val sourceUrl: String?
)
