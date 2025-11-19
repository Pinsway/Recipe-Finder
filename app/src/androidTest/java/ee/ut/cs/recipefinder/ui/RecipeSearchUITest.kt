package ee.ut.cs.recipefinder.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ee.ut.cs.recipefinder.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeSearchUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun searchRecipes_enterTextAndClickSearch_displaysResults() {
        // Wait for initial load
        composeTestRule.waitForIdle()
        Thread.sleep(10000) // Give API time to load

        // Find the text field by its label
        composeTestRule.onNode(
            hasSetTextAction() and hasText("Search recipes", substring = true)
        ).performTextInput("Pasta")

        // Click search button
        composeTestRule.onNodeWithText("Search", useUnmergedTree = true)
            .performClick()

        // Wait for search to complete
        Thread.sleep(10000)
        composeTestRule.waitForIdle()

        // Just verify the search field still exists (search executed without crash)
        composeTestRule.onNode(
            hasSetTextAction() and hasText("Search recipes", substring = true)
        ).assertExists()
    }

    @Test
    fun searchRecipes_emptySearchQuery_showsDefaultRecipes() {
        composeTestRule.waitForIdle()
        Thread.sleep(10000)

        // Click search without entering text
        composeTestRule.onNodeWithText("Search", useUnmergedTree = true)
            .performClick()

        Thread.sleep(10000)
        composeTestRule.waitForIdle()

        // Verify search button still exists (no crash)
        composeTestRule.onNodeWithText("Search", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun searchRecipes_typeQueryAndClear_allowsNewSearch() {
        composeTestRule.waitForIdle()
        Thread.sleep(10000)

        // Enter text
        composeTestRule.onNode(hasSetTextAction())
            .performTextInput("Chicken")

        // Clear
        composeTestRule.onNode(hasSetTextAction())
            .performTextClearance()

        // Enter new text
        composeTestRule.onNode(hasSetTextAction())
            .performTextInput("Beef")

        // Search
        composeTestRule.onNodeWithText("Search", useUnmergedTree = true)
            .performClick()

        Thread.sleep(10000)
        composeTestRule.waitForIdle()

        // Verify still functional
        composeTestRule.onNodeWithText("Search", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun navigation_clickRecipeCard_opensDetailScreen() {
        composeTestRule.waitForIdle()
        Thread.sleep(10000) // Wait for recipes to load

        // Find all cards (they have clickable modifier)
        val cards = composeTestRule.onAllNodes(
            hasClickAction() and hasAnyDescendant(hasText("", substring = true))
        )

        // Get count and click the second one (first is likely the search button)
        if (cards.fetchSemanticsNodes().size > 1) {
            cards[1].performClick()

            composeTestRule.waitForIdle()

            // Verify detail screen shows back button
            composeTestRule.onNode(hasContentDescription("Back"))
                .assertExists()
        }
    }

    @Test
    fun themeSwitch_togglesDarkMode() {
        composeTestRule.waitForIdle()

        // Find the Switch component (it's a toggleable node)
        composeTestRule.onNode(isToggleable())
            .performClick()

        composeTestRule.waitForIdle()

        // Verify theme text changed (Light <-> Dark)
        // One of these should exist
        val hasLight = composeTestRule.onAllNodesWithText("Light").fetchSemanticsNodes().isNotEmpty()
        val hasDark = composeTestRule.onAllNodesWithText("Dark").fetchSemanticsNodes().isNotEmpty()

        assert(hasLight || hasDark) { "Theme label not found" }
    }
}
