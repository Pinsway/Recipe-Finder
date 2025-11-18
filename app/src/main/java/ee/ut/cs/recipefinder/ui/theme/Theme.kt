package ee.ut.cs.recipefinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ee.ut.cs.recipefinder.R

@Composable
fun RecipeFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80,
            background = colorResource(id = R.color.dark_background),
            surface = colorResource(id = R.color.dark_surface),
            onPrimary = Color.Black,
            onSecondary = Color.Black,
            onTertiary = Color.Black,
            onBackground = colorResource(id = R.color.dark_primary_text),
            onSurface = colorResource(id = R.color.dark_primary_text)
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40,
            background = colorResource(id = R.color.light_background),
            surface = colorResource(id = R.color.light_surface),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.White,
            onBackground = colorResource(id = R.color.light_primary_text),
            onSurface = colorResource(id = R.color.light_primary_text)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}