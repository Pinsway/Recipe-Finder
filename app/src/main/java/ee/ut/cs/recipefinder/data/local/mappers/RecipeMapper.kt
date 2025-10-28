package ee.ut.cs.recipefinder.data.local.mappers

import ee.ut.cs.recipefinder.data.local.entity.RecipeEntity
import ee.ut.cs.recipefinder.domain.model.Recipe

fun RecipeEntity.toDomain(): Recipe = Recipe(
    id = id,
    title = title,
    description = description,
    ingredients = ingredients,
    instructions = instructions,
    imageUrl = imageUrl,
    cookTimeMinutes = cookTimeMinutes,
    servings = servings,
    tags = tags,
    sourceUrl = sourceUrl
)

fun Recipe.toEntity(): RecipeEntity = RecipeEntity(
    id = id,
    title = title,
    description = description,
    ingredients = ingredients,
    instructions = instructions,
    imageUrl = imageUrl,
    cookTimeMinutes = cookTimeMinutes,
    servings = servings,
    tags = tags,
    sourceUrl = sourceUrl
)
