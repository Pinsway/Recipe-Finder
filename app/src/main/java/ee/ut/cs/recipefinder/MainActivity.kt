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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import ee.ut.cs.recipefinder.data.remote.MealDbRetrofit
import ee.ut.cs.recipefinder.data.datastore.UserPreferencesManager
class MainActivity : ComponentActivity() {

    // Manager for remembering users choice (Light/Dark Mode)
    private lateinit var userPrefsManager: UserPreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {

        // Startup Splash Screen
        installSplashScreen()

        super.onCreate(savedInstanceState)

        userPrefsManager = UserPreferencesManager(applicationContext)
        // In-app demo DB writes removed; UI now fetches recipes from API in Home screen

        setContent {
            // App starts in Light Theme by Default
            val isDarkTheme by userPrefsManager.theme.collectAsState(initial = false)

            // Saves current selected theme
            val scope = rememberCoroutineScope()

            RecipeFinderTheme(darkTheme = isDarkTheme) {
                AppNavigation(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { newThemeState ->
                        scope.launch {
                            userPrefsManager.saveTheme(newThemeState)
                        }
                    }
                )




                // temporary test for working API call (JSON displayed on screen)
                // var jsonText by remember { mutableStateOf("Loading...") }

                // Launch API call when the screen opens
                // LaunchedEffect(Unit) {
                //    try {
                //        val response = MealDbRetrofit.api.searchMeals("chicken")
                //        jsonText = Gson().toJson(response)
                //    } catch (e: Exception) {
                //        jsonText = "Error: ${e.message}"
                //    }
                //}

                // Display the JSON or error message
                // Text(text = jsonText)
            }
        }
    }
}
