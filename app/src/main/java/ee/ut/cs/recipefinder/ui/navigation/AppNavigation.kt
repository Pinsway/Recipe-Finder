package ee.ut.cs.recipefinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ee.ut.cs.recipefinder.data.datastore.UserPreferencesManager
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.ui.home.HomeScreen
import ee.ut.cs.recipefinder.ui.home.RecipeDetailScreen
import ee.ut.cs.recipefinder.ui.profile.ProfileScreen
import ee.ut.cs.recipefinder.ui.swiper.RecipeSwiper


@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }

        composable("swiper") {
            RecipeSwiper(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            RecipeDetailScreen(navController, recipeId = id)
        }
    }
}
