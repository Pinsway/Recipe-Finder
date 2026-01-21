# Recipe-Finder
Group project made for mobile app development course.  
Our app is for finding recipes. The goal is for the app to display desired recipes based on preferences as an end goal. There will be several options to finding recipes. A general recipe finder where you can choose different preferences and get displayed recipes based on these preferences. Another option will be a recipe swiper, where you get displayed random recipes and swipe them left or right based on whether you like it or not. 

# Members of the team:

| Name             | Role            |
|------------------|-----------------|
| **Erik Anmann**     | Project Manager |
| **Markus Roletsky** | Lead Developer  |
| **Oskar Proos**     | Presenter        |
| **Oskar Pärsim**    | Researcher       |
| **Ruud Tammel**     | Editor           |

# Planned Features:
1) **Recipe finder** - find recipes based on chosen ingredients
2) **Recipe swiper** - find recipes either by liking or disliking them
3) **User profile** - favorite recipes, track available kitchen appliances, leave ratings etc...

# Tools & Frameworks
- **Android Studio** – primary IDE for development
- **Kotlin** – main programming language
- **Jetpack Compose** – modern UI toolkit for building app interfaces
- **Room (Jetpack)** – local database for storing recipes, favorites, and user preferences
- **Firebase Authentication & Firestore** – user login, cloud storage, and syncing data
- **Retrofit** – for connecting to external recipe APIs
- **Coil** – image loading and caching for recipe photos
- **Jetpack Navigation Component / Navigation Compose** – managing multi-screen navigation
- **Material3 (Compose)** – modern UI components and theming
- **Swipe gestures (Compose)** – implementing recipe swipe feature

# Permissions & Privacy
- **Required Device Permissions**
  - The application currently does not require any special device permissions. The only network-related feature is API access for recipe search, which uses standard internet connectivity. No runtime permissions are requested from the user.
- **How User Data Is Handled and Stored**
  - The app stores user-created recipes locally on the device using SharedPreferences.
  - Stored data includes:
    - Recipe title
    - Reciple description
  - When a user adds a recipe:
    - It is saved as JSON list using Gson
    - The data is persisted locally on the device
    - The data remains available after closing and reopening the app
  - On app launch:
    - The home screen loads the saved recipe list from SharedPreferences and displays it
  - This local-only storage ensures that user data is:
    - Not uploaded to any server
    - Not shared with third parties
    - Only accessible on the user's own device
  - The app connects to TheMealDB API to fetch public recipe data when the user performs a search. No personal user data is sent to the API. Only the search query is transmitted. API responses are used only for displaying recipes inside the app.
  - The app includes error handling to prevent crashes in case of:
    - No internet connection
    - Empty or invalid API responses
    - Unexpected exceptions
