package ee.ut.cs.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ee.ut.cs.recipefinder.ui.navigation.AppNavigation
import ee.ut.cs.recipefinder.ui.theme.RecipeFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeFinderTheme {
                AppNavigation()
            }
        }
    }
}