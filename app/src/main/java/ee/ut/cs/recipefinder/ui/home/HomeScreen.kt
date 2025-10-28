package ee.ut.cs.recipefinder.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import ee.ut.cs.recipefinder.data.RecipeRepository
import ee.ut.cs.recipefinder.data.local.AppDatabase
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.domain.util.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "recipes.db").build()
    }
    val repo = remember { RecipeRepository(db.recipeDao()) }

    var recipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        loading = true
        error = null
        // First try empty search which often returns many meals
        val initial = repo.fetchRecipesFromApi("")
        if (initial is Resource.Success && initial.data.isNotEmpty()) {
            recipes = initial.data
            // If not many items, broaden by fetching letters
            if (recipes.size < 40) {
                when (val more = repo.fetchRecipesByFirstLetters(('a'..'z').toList())) {
                    is Resource.Success -> recipes = (recipes + more.data).distinctBy { it.id }
                    is Resource.Error -> error = more.message
                    is Resource.Loading -> Unit
                }
            }
            loading = false
        } else {
            // Fall back to fetching by letters only
            when (val more = repo.fetchRecipesByFirstLetters(('a'..'z').toList())) {
                is Resource.Success -> {
                    recipes = more.data
                    loading = false
                }
                is Resource.Error -> {
                    error = more.message
                    // Try cached
                    recipes = repo.getAllRecipes()
                    loading = false
                }
                is Resource.Loading -> Unit
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Recipe Finder") }) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* already on home */ },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    label = { Text("Profile") },
                    icon = { Icon(Icons.Default.Person, null) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                error != null && recipes.isEmpty() -> {
                    Text(error ?: "Error", modifier = Modifier.align(Alignment.Center))
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Search controls at the top
                        item(key = "search") {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = searchQuery,
                                    onValueChange = { searchQuery = it },
                                    label = { Text("Search recipes") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(Modifier.height(8.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(onClick = {
                                        scope.launch {
                                            loading = true
                                            error = null
                                            if (searchQuery.isBlank()) {
                                                // Reset to default broader feed
                                                val initial = repo.fetchRecipesFromApi("")
                                                if (initial is Resource.Success && initial.data.isNotEmpty()) {
                                                    recipes = initial.data
                                                    if (recipes.size < 40) {
                                                        when (val more = repo.fetchRecipesByFirstLetters(('a'..'z').toList())) {
                                                            is Resource.Success -> recipes = (recipes + more.data).distinctBy { it.id }
                                                            is Resource.Error -> error = more.message
                                                            is Resource.Loading -> Unit
                                                        }
                                                    }
                                                } else {
                                                    when (val more = repo.fetchRecipesByFirstLetters(('a'..'z').toList())) {
                                                        is Resource.Success -> recipes = more.data
                                                        is Resource.Error -> {
                                                            error = more.message
                                                            recipes = repo.getAllRecipes()
                                                        }
                                                        is Resource.Loading -> Unit
                                                    }
                                                }
                                                loading = false
                                            } else {
                                                when (val res = repo.fetchRecipesFromApi(searchQuery)) {
                                                    is Resource.Success -> {
                                                        recipes = res.data
                                                        loading = false
                                                    }
                                                    is Resource.Error -> {
                                                        error = res.message
                                                        loading = false
                                                    }
                                                    is Resource.Loading -> Unit
                                                }
                                            }
                                        }
                                    }) { Text("Search") }
                                    if (loading) {
                                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                    }
                                }
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                        items(recipes) { recipe ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable { navController.navigate("detail/${recipe.id}") }
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text(recipe.title, style = MaterialTheme.typography.titleMedium)
                                    if (recipe.tags.isNotEmpty()) {
                                        Spacer(Modifier.height(4.dp))
                                        Text(recipe.tags.joinToString(", "), style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                        if (!loading && recipes.isEmpty()) {
                            item(key = "noresults") {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp),
                                    contentAlignment = Alignment.Center
                                ) { Text("No recipes found") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: String) {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "recipes.db").build()
    }
    val repo = remember { RecipeRepository(db.recipeDao()) }

    var recipe by remember { mutableStateOf<Recipe?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(recipeId) {
        loading = true
        error = null
        // Ensure we have data; if not, you could re-fetch
        recipe = repo.getRecipeById(recipeId)
        if (recipe == null) {
            // As a fallback, try fetching by title keyword
            try {
                val res = repo.fetchRecipesFromApi("")
                if (res is Resource.Success) {
                    recipe = res.data.firstOrNull { it.id == recipeId }
                }
            } catch (e: Exception) {
                error = "Failed to load recipe"
            }
        }
        loading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            when {
                loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                error != null -> Text(error ?: "Error", modifier = Modifier.align(Alignment.Center))
                recipe == null -> Text("Recipe not found", modifier = Modifier.align(Alignment.Center))
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(recipe!!.title, style = MaterialTheme.typography.headlineSmall)
                        if (recipe!!.ingredients.isNotEmpty()) {
                            Text("Ingredients", style = MaterialTheme.typography.titleMedium)
                            recipe!!.ingredients.forEach { ing ->
                                val qty = ing.quantity?.toString() ?: ""
                                val unit = ing.unit ?: ""
                                Text("- ${ing.name}${if (qty.isNotBlank() || unit.isNotBlank()) ": $qty $unit" else ""}")
                            }
                        }
                        if (!recipe!!.instructions.isNullOrBlank()) {
                            Text("Instructions", style = MaterialTheme.typography.titleMedium)
                            Text(recipe!!.instructions!!)
                        }
                        OutlinedButton(onClick = { navController.popBackStack() }) {
                            Text("Back")
                        }
                    }
                }
            }
        }
    }
}
