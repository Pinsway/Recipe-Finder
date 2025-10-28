# Step 3 - API Integration

## What API was chosen and why?
We chose **[TheMealDB API](https://www.themealdb.com/api.php)** because it provides a choice of meal and recipe data from around the world.
The API was also simple to integrate with **Retrofit**, as it uses Json responses and supports many search endpoints.

## Example API Endpoint
Searching by name **[Chicken](https://www.themealdb.com/api/json/v1/1/search.php?s=chicken)**, it returns a JSON object containing a list of meals that include chicken.

## Error Handling
The app doesn't crash in case of:
- No Internet Connection
- Empty or Invalid API Response
- Unexpected Exceptions

Error handling is implemented using try-catch blocks.
If an exception occurs the user sees an error message
