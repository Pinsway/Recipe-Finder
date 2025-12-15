package ee.ut.cs.recipefinder.ui.swiper

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Swipe
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSwiper(navController: NavController, isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Recipe Swiper") },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (isDarkTheme) "Dark" else "Light")
                        Spacer(Modifier.width(8.dp))
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = onThemeChange // This triggers the change in MainActivity
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
                    onClick = { navController.navigate("profile") },
                    label = { Text("Profile") },
                    icon = { Icon(Icons.Default.Person, null) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Testing Swipe", style = MaterialTheme.typography.headlineSmall)

        }


    }
}