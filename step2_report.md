# Step 2 Report - Recipe finder

## What data is store locally
The app stores a list of **recipes** locally using **SharedPreferences**.
Each recipe currently contains:
- Title
- Description
- (Other fields ingredients, image URL, tags, which are defined in the data model but not yet used)

Whenever a user adds a recipe on the Add Recipe screen, it is saved in SharedPreferences as a JSON list using Gson.
When the app opens, the Home screen loads that list and displays all saved recipes.
This allows the recipes to persist even after closing the app.

---

## Challenges and Solutions

### Challenge 1 - Implementing local data storage
Implementing logic to store data locally was something new to us. 
We used AI guidance and Android documentations to help us understand how SharedPreferences and Gson work together.
