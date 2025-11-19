package ee.ut.cs.recipefinder.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class UserProfileTest {

    @Test
    fun construct_withIdOnly_setsDefaultFields() {
        val user = UserProfile(id = "user123")
        assertEquals("user123", user.id)
        assertEquals(null, user.name)
        assertEquals(null, user.email)
        assertEquals(emptyList<String>(), user.favoriteRecipeIds)
        assertEquals(emptyList<String>(), user.preferredIngredients)
        assertEquals(emptyList<String>(), user.excludedIngredients)
    }

    @Test
    fun construct_withAllFields_setsFieldsCorrectly() {
        val favorites = listOf("recipe1", "recipe2")
        val preferred = listOf("sugar", "milk")
        val excluded = listOf("nuts", "shellfish")
        val user = UserProfile(
            id = "user456",
            name = "Alice",
            email = "alice@example.com",
            favoriteRecipeIds = favorites,
            preferredIngredients = preferred,
            excludedIngredients = excluded
        )
        assertEquals("user456", user.id)
        assertEquals("Alice", user.name)
        assertEquals("alice@example.com", user.email)
        assertEquals(favorites, user.favoriteRecipeIds)
        assertEquals(preferred, user.preferredIngredients)
        assertEquals(excluded, user.excludedIngredients)
    }

    @Test
    fun equality_sameData_objectsAreEqual() {
        val favorites = listOf("r1", "r2")
        val preferred = listOf("apple")
        val excluded = listOf("egg")
        val u1 = UserProfile(
            id = "id",
            name = "Bob",
            email = "bob@example.com",
            favoriteRecipeIds = favorites,
            preferredIngredients = preferred,
            excludedIngredients = excluded
        )
        val u2 = UserProfile(
            id = "id",
            name = "Bob",
            email = "bob@example.com",
            favoriteRecipeIds = favorites,
            preferredIngredients = preferred,
            excludedIngredients = excluded
        )
        assertEquals(u1, u2)
    }

    @Test
    fun equality_differentIds_objectsAreNotEqual() {
        val u1 = UserProfile(id = "id1")
        val u2 = UserProfile(id = "id2")
        assertNotEquals(u1, u2)
    }
}
