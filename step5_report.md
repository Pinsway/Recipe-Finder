# Step 5 – Final Presentation & Reflection

## App Overview
Recipe Finder is a mobile application that helps users discover recipes through both structured search and inspiration-based browsing. The app supports recipe search, random discovery, detailed recipe views, and a favorites system.

## Architecture Summary
The application follows a clean architecture structure:
- UI layer implemented with Jetpack Compose
- Domain layer containing core models
- Data layer handling API, local database, and repositories

Navigation is handled using a single NavController with lightweight arguments.

## Features Implemented
- Recipe search by keyword
- Discover view with Like / Skip actions
- Recipe detail screen with ingredients, instructions, and images
- Liked recipes list
- Local caching using Room
- Light and Dark mode support

## API Integration
An external recipe API is used to fetch recipe data. API responses are mapped to domain models and cached locally. Image URLs from the API are displayed consistently across discovery and detail views.

## Libraries Used

- Android Studio – primary IDE for development
- Kotlin – main programming language
- Jetpack Compose – modern UI toolkit for building app interfaces
- Room (Jetpack) – local database for storing recipes, favorites, and user preferences
- Firebase Authentication & Firestore – user login, cloud storage, and syncing data
- Retrofit – for connecting to external recipe APIs
- Coil – image loading and caching for recipe photos
- Jetpack Navigation Component / Navigation Compose – managing multi-screen navigation
- Material3 (Compose) – modern UI components and theming

## Challenges & Lessons Learned
**Challenges**
- Managing state in Compose
- Handling API edge cases
- Ensuring UI consistency across screens

**Lessons Learned**
- Importance of clean architecture
- Improved Compose and Kotlin skills
- Better teamwork and task coordination

## Limitations & Future Improvements
**Limitations**
- No user authentication
- Basic recommendation logic

**Future Improvements**
- Personalized recommendations
- User profiles
- Offline-first enhancements
