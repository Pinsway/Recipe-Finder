package ee.ut.cs.recipefinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ee.ut.cs.recipefinder.ui.home.HomeScreen
import ee.ut.cs.recipefinder.ui.home.RecipeDetailScreen
import ee.ut.cs.recipefinder.ui.profile.ProfileScreen

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
        composable("profile") { ProfileScreen(navController) }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            RecipeDetailScreen(navController, recipeId = id)
        }
    }
}
