package ee.ut.cs.recipefinder.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ee.ut.cs.recipefinder.domain.model.Recipe

/**
 * @deprecated
 */
object RecipeStorage {
    private const val PREF_NAME = "recipe_prefs"
    private const val KEY_RECIPES = "recipes"

    fun saveRecipes(context: Context, recipes: List<Recipe>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(recipes)
        editor.putString(KEY_RECIPES, json)
        editor.apply()
    }

    fun loadRecipes(context: Context): MutableList<Recipe> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_RECIPES, null)
        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        return if (json != null) Gson().fromJson(json, type) else mutableListOf()
    }
}