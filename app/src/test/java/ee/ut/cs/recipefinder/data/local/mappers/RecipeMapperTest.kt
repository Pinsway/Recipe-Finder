package ee.ut.cs.recipefinder.data.local.mappers

import ee.ut.cs.recipefinder.data.local.entity.RecipeEntity
import ee.ut.cs.recipefinder.domain.model.Ingredient
import ee.ut.cs.recipefinder.domain.model.Recipe
import org.junit.Test
import org.junit.Assert.assertEquals

class RecipeMapperTest {

    @Test
    fun toDomain_mapsAllFieldsCorrectly() {
        val ingredients = listOf(Ingredient("Egg", 2.0, "pc"), Ingredient("Milk", 1.5, "dl"))
        val tags = listOf("breakfast", "classic")
        val entity = RecipeEntity(
            id = "id1",
            title = "Omelette",
            description = "Fluffy omelette",
            ingredients = ingredients,
            instructions = "Beat eggs, cook in pan.",
            imageUrl = "http://example.com/image.jpg",
            cookTimeMinutes = 10,
            servings = 2,
            tags = tags,
            sourceUrl = "http://example.com/source"
        )

        val domain = entity.toDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.description, domain.description)
        assertEquals(entity.ingredients, domain.ingredients)
        assertEquals(entity.instructions, domain.instructions)
        assertEquals(entity.imageUrl, domain.imageUrl)
        assertEquals(entity.cookTimeMinutes, domain.cookTimeMinutes)
        assertEquals(entity.servings, domain.servings)
        assertEquals(entity.tags, domain.tags)
        assertEquals(entity.sourceUrl, domain.sourceUrl)
    }

    @Test
    fun toEntity_mapsAllFieldsCorrectly() {
        val ingredients = listOf(Ingredient("Egg", 2.0, "pc"), Ingredient("Milk", 1.5, "dl"))
        val tags = listOf("breakfast", "classic")
        val recipe = Recipe(
            id = "id2",
            title = "Pancake",
            description = "Simple pancake",
            ingredients = ingredients,
            instructions = "Mix ingredients, fry.",
            imageUrl = "http://example.com/pancake.jpg",
            cookTimeMinutes = 15,
            servings = 4,
            tags = tags,
            sourceUrl = "http://example.com/pancake"
        )

        val entity = recipe.toEntity()
        assertEquals(recipe.id, entity.id)
        assertEquals(recipe.title, entity.title)
        assertEquals(recipe.description, entity.description)
        assertEquals(recipe.ingredients, entity.ingredients)
        assertEquals(recipe.instructions, entity.instructions)
        assertEquals(recipe.imageUrl, entity.imageUrl)
        assertEquals(recipe.cookTimeMinutes, entity.cookTimeMinutes)
        assertEquals(recipe.servings, entity.servings)
        assertEquals(recipe.tags, entity.tags)
        assertEquals(recipe.sourceUrl, entity.sourceUrl)
    }

    @Test
    fun mapping_emptyFields_mapsCorrectly() {
        val recipe = Recipe(
            id = "id3",
            title = "Empty Recipe"
            // All other fields left as default (null/empty)
        )
        val entity = recipe.toEntity()
        val domain = entity.toDomain()
        assertEquals(recipe, domain) // Round-trip should match
    }
}
