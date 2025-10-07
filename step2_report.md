# Step 2 Report

## Local Data
- Recipes are persisted in Android `SharedPreferences` under the file name `recipe_prefs` and key `recipes`.
- Each entry is serialized as JSON using Gson, preserving the full `Recipe` data class (ID, title, optional description, ingredients, instructions, metadata).
- Data reads happen whenever the Home screen needs to display the list, ensuring the UI reflects the latest saved state.

## Challenges & Solutions
