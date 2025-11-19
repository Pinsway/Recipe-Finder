# Step 4 - Polishing + Testing + APK

## Testing strategy
**Converter Tests**
* Validate correct JSON serialization and deserialization for: IngredientListConverter, StringListConverter
* Confirm null-safety behavior (null → "[]", empty → empty list).
* Ensure conversions are inverse operations (fromList → toList → original list).

**Entity tests**
* Verify that RecipeEntity correctly initializes all fields.
* Ensure default values are set when optional fields are omitted.
* Check equality logic (same data = equal, differing IDs = not equal).

**Mapper Tests**
* Validate bidirectional Mapping between RecipeEntity & Recipe.
* Confirm all fields map correctly.
* Test round-trip correctness (Recipe -> Entity -> Recipe).

## Build process for APK
1. Android Studio → Build > Select Build Variant > release
2. Build the APK using Android Studio
3. Build App Bundle

## Known bugs or limitations
* Splash screens background depends whether the device is set to use light / dark mode. So the app can boot up with a lightmode splash screen and dark mode application.
