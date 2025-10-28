package ee.ut.cs.recipefinder.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<String>?): String = gson.toJson(value ?: emptyList<String>())

    @TypeConverter
    fun toList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}
