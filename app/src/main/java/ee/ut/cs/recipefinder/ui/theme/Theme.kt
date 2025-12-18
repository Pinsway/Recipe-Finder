package ee.ut.cs.recipefinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RecipeFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = DarkPrimary,
            secondary = DarkSecondary,
            tertiary = DarkTertiary,
            background = DarkBackground,
            surface = DarkSurface,
            onPrimary = Color.Black,
            onSecondary = Color.White,
            onTertiary = Color.Black,
            onBackground = DarkTextPrimary,
            onSurface = DarkTextPrimary
        )
    } else {
        lightColorScheme(
            primary = LightPrimary,
            secondary = LightSecondary,
            tertiary = LightTertiary,
            background = LightBackground,
            surface = LightSurface,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onTertiary = Color.White,
            onBackground = LightTextPrimary,
            onSurface = LightTextPrimary
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
