package ee.ut.cs.recipefinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ee.ut.cs.recipefinder.data.local.dao.RecipeDao
import ee.ut.cs.recipefinder.data.local.entity.RecipeEntity
import ee.ut.cs.recipefinder.data.local.converters.*

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = true)
@TypeConverters(IngredientListConverter::class, StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
