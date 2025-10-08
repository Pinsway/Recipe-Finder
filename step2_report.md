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

### **1. Initializing the project**
**Challenge:** Getting the project set up was more complicated than expected. Our inital repository was not initialized as an Android project, but just had the user-stories and sketches.

**Solution:** We used the practise sessions repository as a template for our own.

### **2. Implementing local data storage**
**Challenge:** Implementing logic to store data locally was something new to us. 

**Solution:** We used AI guidance and Android documentations to help us understand how SharedPreferences and Gson work together.

### **3. Implementing basic navigation**
**Challenge:** Setting up navigation between the three pages (Home, Add Item, Profile) required understanding the Navigation Compose framework and managing screen states correctly.  

**Solution:** We followed Android’s Navigation Compose documentation and used some help from AI to get a working solution.
