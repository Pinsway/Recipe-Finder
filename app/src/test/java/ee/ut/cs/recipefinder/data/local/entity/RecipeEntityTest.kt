package ee.ut.cs.recipefinder.data.local.entity

import ee.ut.cs.recipefinder.domain.model.Ingredient
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class RecipeEntityTest {

    @Test
    fun construct_withAllFields_setsFieldsCorrectly() {
        val ingredients = listOf(Ingredient("Egg", 2.0, "pc"), Ingredient("Milk", 1.5, "dl"))
        val tags = listOf("breakfast", "easy")
        val entity = RecipeEntity(
            id = "id100",
            title = "Omelette",
            description = "Quick and easy",
            ingredients = ingredients,
            instructions = "Beat eggs, cook in pan.",
            imageUrl = "http://example.com/image.jpg",
            cookTimeMinutes = 12,
            servings = 1,
            tags = tags,
            sourceUrl = "http://example.com/source"
        )

        assertEquals("id100", entity.id)
        assertEquals("Omelette", entity.title)
        assertEquals("Quick and easy", entity.description)
        assertEquals(ingredients, entity.ingredients)
        assertEquals("Beat eggs, cook in pan.", entity.instructions)
        assertEquals("http://example.com/image.jpg", entity.imageUrl)
        assertEquals(12, entity.cookTimeMinutes)
        assertEquals(1, entity.servings)
        assertEquals(tags, entity.tags)
        assertEquals("http://example.com/source", entity.sourceUrl)
    }

    @Test
    fun construct_withMinimalFields_setsDefaults() {
        val entity = RecipeEntity(
            id = "id200",
            title = "Empty Recipe",
            description = null,
            ingredients = emptyList(),
            instructions = null,
            imageUrl = null,
            cookTimeMinutes = null,
            servings = null,
            tags = emptyList(),
            sourceUrl = null
        )
        assertEquals("id200", entity.id)
        assertEquals("Empty Recipe", entity.title)
        assertEquals(null, entity.description)
        assertEquals(emptyList<Ingredient>(), entity.ingredients)
        assertEquals(null, entity.instructions)
        assertEquals(null, entity.imageUrl)
        assertEquals(null, entity.cookTimeMinutes)
        assertEquals(null, entity.servings)
        assertEquals(emptyList<String>(), entity.tags)
        assertEquals(null, entity.sourceUrl)
    }

    @Test
    fun equality_sameData_objectsAreEqual() {
        val entity1 = RecipeEntity(
            id = "id77",
            title = "Test",
            description = null,
            ingredients = emptyList(),
            instructions = null,
            imageUrl = null,
            cookTimeMinutes = null,
            servings = null,
            tags = emptyList(),
            sourceUrl = null
        )
        val entity2 = RecipeEntity(
            id = "id77",
            title = "Test",
            description = null,
            ingredients = emptyList(),
            instructions = null,
            imageUrl = null,
            cookTimeMinutes = null,
            servings = null,
            tags = emptyList(),
            sourceUrl = null
        )
        assertEquals(entity1, entity2)
    }

    @Test
    fun equality_differentId_objectsAreNotEqual() {
        val e1 = RecipeEntity(
            id = "idX",
            title = "Test",
            description = null,
            ingredients = emptyList(),
            instructions = null,
            imageUrl = null,
            cookTimeMinutes = null,
            servings = null,
            tags = emptyList(),
            sourceUrl = null
        )
        val e2 = e1.copy(id = "idY")
        assertNotEquals(e1, e2)
    }
}
