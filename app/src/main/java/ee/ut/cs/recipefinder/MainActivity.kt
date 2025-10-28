package ee.ut.cs.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import ee.ut.cs.recipefinder.data.RecipeRepository
import ee.ut.cs.recipefinder.data.local.AppDatabase
import ee.ut.cs.recipefinder.domain.model.Ingredient
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.ui.navigation.AppNavigation
import ee.ut.cs.recipefinder.ui.theme.RecipeFinderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Example of database working
        val db = Room.inMemoryDatabaseBuilder(
            applicationContext,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        val repo = RecipeRepository(db.recipeDao())

        CoroutineScope(Dispatchers.IO).launch {
            repo.clearAll()

            val recipe = Recipe(
                title = "Chocolate Cake",
                description = "Rich and moist cake.",
                ingredients = listOf(
                    Ingredient("Flour", 200.0, "g"),
                    Ingredient("Sugar", 100.0, "g"),
                    Ingredient("Cocoa", 50.0, "g")
                ),
                instructions = "Mix ingredients and bake for 40 minutes.",
                cookTimeMinutes = 40,
                servings = 6,
                tags = listOf("Dessert", "Chocolate")
            )

            repo.saveRecipe(recipe)

            repo.getAllRecipes().forEach { println(it.title) }
        }

        setContent {
            RecipeFinderTheme {
                AppNavigation()
            }
        }
    }
}