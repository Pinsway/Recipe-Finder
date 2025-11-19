package ee.ut.cs.recipefinder.data.local.converters

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import ee.ut.cs.recipefinder.domain.model.Ingredient

class IngredientListConverterTest {

    private val converter = IngredientListConverter()

    @Test
    fun fromList_and_toList_areInverse() {
        val ingredients = listOf(
            Ingredient(name = "Tomato", quantity = 2.0, unit = "pcs"),
            Ingredient(name = "Salt", quantity = 1.0, unit = "tsp"),
            Ingredient(name = "Pepper", quantity = null, unit = null)
        )
        val json = converter.fromList(ingredients)
        val result = converter.toList(json)
        assertEquals(ingredients, result)
    }

    @Test
    fun fromList_withNull_returnsEmptyJsonArray() {
        val json = converter.fromList(null)
        assertEquals("[]", json)
    }

    @Test
    fun toList_withEmptyJson_returnsEmptyList() {
        val result = converter.toList("[]")
        assertTrue(result.isEmpty())
    }
}
