package ee.ut.cs.recipefinder.data.local.converters

import org.junit.Test
import org.junit.Assert.assertEquals

class StringListConverterTest {
    private val converter = StringListConverter()

    @Test
    fun fromList_and_toList_areInverse() {
        val data = listOf("Egg", "Milk", "Bread")
        val str = converter.fromList(data)
        val result = converter.toList(str)
        assertEquals(data, result)
    }

    @Test
    fun fromList_withNull_returnsEmptyString() {
        val str = converter.fromList(null)
        assertEquals("[]", str)
    }
}