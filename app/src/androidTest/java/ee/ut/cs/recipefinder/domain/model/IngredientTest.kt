package ee.ut.cs.recipefinder.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class IngredientTest {

    @Test
    fun construct_withNameOnly_setsDefaults() {
        val ingredient = Ingredient(name = "Sugar")
        assertEquals("Sugar", ingredient.name)
        assertEquals(null, ingredient.quantity)
        assertEquals(null, ingredient.unit)
    }

    @Test
    fun construct_withAllFields_setsFieldsCorrectly() {
        val ingredient = Ingredient(name = "Milk", quantity = 2.0, unit = "dl")
        assertEquals("Milk", ingredient.name)
        assertEquals(2.0, ingredient.quantity)
        assertEquals("dl", ingredient.unit)
    }

    @Test
    fun equality_sameFields_objectsAreEqual() {
        val i1 = Ingredient(name = "Egg", quantity = 1.0, unit = "pc")
        val i2 = Ingredient(name = "Egg", quantity = 1.0, unit = "pc")
        assertEquals(i1, i2)
    }

    @Test
    fun equality_differentFields_objectsAreNotEqual() {
        val i1 = Ingredient(name = "Egg", quantity = 1.0, unit = "pc")
        val i2 = Ingredient(name = "Egg", quantity = 2.0, unit = "pc")
        assertNotEquals(i1, i2)
    }

    @Test
    fun construct_withNullQuantityAndUnit_handlesNulls() {
        val i = Ingredient(name = "Butter", quantity = null, unit = null)
        assertEquals("Butter", i.name)
        assertEquals(null, i.quantity)
        assertEquals(null, i.unit)
    }
}
