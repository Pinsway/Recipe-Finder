package ee.ut.cs.recipefinder.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull

class RecipeTest {

    @Test
    fun construct_withTitleOnly_setsDefaultFields() {
        val recipe = Recipe(title = "Pancakes")
        assertNotNull(recipe.id) // ID is auto-generated
        assertEquals("Pancakes", recipe.title)
        assertEquals(null, recipe.description)
        assertEquals(emptyList<Ingredient>(), recipe.ingredients)
        assertEquals(null, recipe.instructions)
        assertEquals(null, recipe.imageUrl)
        assertEquals(null, recipe.cookTimeMinutes)
        assertEquals(null, recipe.servings)
        assertEquals(emptyList<String>(), recipe.tags)
        assertEquals(null, recipe.sourceUrl)
    }

    @Test
    fun construct_withAllFields_setsFieldsCorrectly() {
        val ingredients = listOf(
            Ingredient("Egg", 2.0, "pc"),
            Ingredient("Milk", 1.5, "dl")
        )
        val tags = listOf("breakfast", "easy")

        val recipe = Recipe(
            title = "Omelette",
            description = "Classic breakfast",
            ingredients = ingredients,
            instructions = "Beat eggs, cook in pan.",
            imageUrl = "http://example.com/img.png",
            cookTimeMinutes = 10,
            servings = 1,
            tags = tags,
            sourceUrl = "http://example.com/recipe"
        )

        assertEquals("Omelette", recipe.title)
        assertEquals("Classic breakfast", recipe.description)
        assertEquals(ingredients, recipe.ingredients)
        assertEquals("Beat eggs, cook in pan.", recipe.instructions)
        assertEquals("http://example.com/img.png", recipe.imageUrl)
        assertEquals(10, recipe.cookTimeMinutes)
        assertEquals(1, recipe.servings)
        assertEquals(tags, recipe.tags)
        assertEquals("http://example.com/recipe", recipe.sourceUrl)
    }

    @Test
    fun equality_sameDataWithDifferentId_objectsAreNotEqual() {
        val ingredients = listOf(Ingredient("Salt"))
        val r1 = Recipe(
            id = "id1",
            title = "Soup",
            ingredients = ingredients
        )
        val r2 = Recipe(
            id = "id2",
            title = "Soup",
            ingredients = ingredients
        )
        assertNotEquals(r1, r2) // IDs are different!
    }

    @Test
    fun equality_sameIdAndData_objectsAreEqual() {
        val ingredients = listOf(Ingredient("Salt"))
        val tags = listOf("vegan")
        val r1 = Recipe(
            id = "sameId",
            title = "Soup",
            ingredients = ingredients,
            tags = tags
        )
        val r2 = Recipe(
            id = "sameId",
            title = "Soup",
            ingredients = ingredients,
            tags = tags
        )
        assertEquals(r1, r2)
    }
}
