package ee.ut.cs.recipefinder.data.local.dao

import androidx.room.*
import ee.ut.cs.recipefinder.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getById(id: String): RecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<RecipeEntity>)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Query("DELETE FROM recipes")
    suspend fun clearAll()
}
