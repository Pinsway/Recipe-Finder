package ee.ut.cs.recipefinder.ui.swiper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Swipe
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import ee.ut.cs.recipefinder.data.RecipeRepository
import ee.ut.cs.recipefinder.data.local.AppDatabase
import ee.ut.cs.recipefinder.domain.model.Recipe
import ee.ut.cs.recipefinder.domain.util.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSwiper(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    likedRecipes: SnapshotStateList<Recipe>
) {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "recipes.db").build()
    }
    val repository = remember { RecipeRepository(db.recipeDao()) }
    var recipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    fun refreshRecipes() {
        scope.launch {
            loading = true
            error = null
            var fetched: List<Recipe> = emptyList()
            when (val res = repository.fetchRecipesFromApi("")) {
                is Resource.Success -> fetched = res.data
                is Resource.Error -> error = res.message
                is Resource.Loading -> Unit
            }
            if (fetched.isEmpty()) {
                when (val res = repository.fetchRecipesByFirstLetters(listOf('a', 'b', 'c', 'd', 'e'))) {
                    is Resource.Success -> fetched = res.data
                    is Resource.Error -> if (error == null) error = res.message
                    is Resource.Loading -> Unit
                }
            }
            if (fetched.isEmpty()) {
                val cached = repository.getAllRecipes()
                if (cached.isNotEmpty()) {
                    fetched = cached
                }
            }
            recipes = fetched.shuffled()
            currentIndex = 0
            loading = false
        }
    }

    LaunchedEffect(Unit) {
        refreshRecipes()
    }

    val currentRecipe = recipes.getOrNull(currentIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe Swiper") },
                actions = {
                    IconButton(onClick = { navController.navigate("liked") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Liked recipes")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (isDarkTheme) "Dark" else "Light")
                        Spacer(Modifier.width(8.dp))
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = onThemeChange
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, null) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* already on Swiper */ },
                    label = { Text("Swiper") },
                    icon = { Icon(Icons.Default.Swipe, null) }

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("liked") },
                    label = { Text("Liked") },
                    icon = { Icon(Icons.Default.Favorite, null) }
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
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                currentRecipe == null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = error ?: "No more recipes to show.",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = { refreshRecipes() }) {
                            Text("Reload feed")
                        }
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column {
                                AsyncImage(
                                    model = currentRecipe.imageUrl,
                                    contentDescription = currentRecipe.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(320.dp),
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.Center
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        currentRecipe.title,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    if (currentRecipe.tags.isNotEmpty()) {
                                        Spacer(Modifier.height(6.dp))
                                        Text(
                                            currentRecipe.tags.joinToString(", "),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { currentIndex++ },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Skip")
                            }
                            Button(
                                onClick = {
                                    if (likedRecipes.none { it.id == currentRecipe.id }) {
                                        likedRecipes.add(currentRecipe)
                                    }
                                    currentIndex++
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Like")
                            }
                        }
                    }
                }
            }
        }
    }
}
