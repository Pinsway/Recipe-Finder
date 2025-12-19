package ee.ut.cs.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
            }
        }
    }
}
