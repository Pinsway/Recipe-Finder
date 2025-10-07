package ee.ut.cs.recipefinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ee.ut.cs.recipefinder.ui.home.HomeScreen
import ee.ut.cs.recipefinder.ui.add.AddRecipeScreen
import ee.ut.cs.recipefinder.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("add") { AddRecipeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}
