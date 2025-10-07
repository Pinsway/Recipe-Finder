package ee.ut.cs.recipefinder.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ee.ut.cs.recipefinder.data.RecipeStorage
import ee.ut.cs.recipefinder.domain.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(navController: NavController) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Recipe") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Recipe Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            if (showError) {
                Text("Please enter a title.", color = MaterialTheme.colorScheme.error)
            }
            Button(
                onClick = {
                    if (title.isBlank()) {
                        showError = true
                    } else {
                        val newRecipe = Recipe(title = title, description = description)
                        val list = RecipeStorage.loadRecipes(context)
                        list.add(newRecipe)
                        RecipeStorage.saveRecipes(context, list)
                        navController.popBackStack() // go back to Home
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Recipe")
            }
            OutlinedButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Home")
            }
        }
    }
}
