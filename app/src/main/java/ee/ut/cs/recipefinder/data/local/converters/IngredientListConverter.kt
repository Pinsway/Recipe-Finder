package ee.ut.cs.recipefinder.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ee.ut.cs.recipefinder.domain.model.Ingredient

class IngredientListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<Ingredient>?): String = gson.toJson(value ?: emptyList<Ingredient>())

    @TypeConverter
    fun toList(value: String): List<Ingredient> {
        val type = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, type)
    }
}
