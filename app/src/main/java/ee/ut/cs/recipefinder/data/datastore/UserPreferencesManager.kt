package ee.ut.cs.recipefinder.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferencesManager(private val context: Context) {

    companion object {
        private val FAVORITE_RECIPES = stringSetPreferencesKey("favorite_recipes")
        private val PREFERRED_INGREDIENTS = stringSetPreferencesKey("preferred_ingredients")
        private val EXCLUDED_INGREDIENTS = stringSetPreferencesKey("excluded_ingredients")
    }

    val favoriteRecipes: Flow<Set<String>> = context.dataStore.data.map { prefs ->
        prefs[FAVORITE_RECIPES] ?: emptySet()
    }

    suspend fun toggleFavorite(recipeId: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[FAVORITE_RECIPES] ?: emptySet()
            prefs[FAVORITE_RECIPES] =
                if (current.contains(recipeId)) current - recipeId else current + recipeId
        }
    }

    suspend fun savePreferredIngredients(ingredients: Set<String>) {
        context.dataStore.edit { prefs -> prefs[PREFERRED_INGREDIENTS] = ingredients }
    }

    suspend fun saveExcludedIngredients(ingredients: Set<String>) {
        context.dataStore.edit { prefs -> prefs[EXCLUDED_INGREDIENTS] = ingredients }
    }
}
