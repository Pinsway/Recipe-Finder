package ee.ut.cs.recipefinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.ui.home.HomeScreen
import ee.ut.cs.recipefinder.ui.home.RecipeDetailScreen
import ee.ut.cs.recipefinder.ui.profile.ProfileScreen
import ee.ut.cs.recipefinder.ui.swiper.LikedRecipesScreen
import ee.ut.cs.recipefinder.ui.swiper.RecipeSwiper


@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val likedRecipes = remember { mutableStateListOf<Recipe>() }

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
                onThemeChange = onThemeChange,
                likedRecipes = likedRecipes
            )
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable("liked") {
            LikedRecipesScreen(
                navController = navController,
                likedRecipes = likedRecipes,
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
