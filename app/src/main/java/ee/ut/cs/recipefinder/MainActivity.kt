package ee.ut.cs.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// import androidx.room.Room
// import ee.ut.cs.recipefinder.data.RecipeRepository
// import ee.ut.cs.recipefinder.data.local.AppDatabase
// import ee.ut.cs.recipefinder.domain.model.Ingredient
// import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.ui.navigation.AppNavigation
import ee.ut.cs.recipefinder.ui.theme.RecipeFinderTheme
 

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // In-app demo DB writes removed; UI now fetches recipes from API in Home screen

        setContent {
            RecipeFinderTheme {
                AppNavigation()
            }
        }
    }
}
