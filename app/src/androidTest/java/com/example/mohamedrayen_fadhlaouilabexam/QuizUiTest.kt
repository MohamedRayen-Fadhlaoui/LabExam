package com.example.mohamedrayen_fadhlaouilabexam

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class QuizUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAppBrandingAndNavigationFlow() {
        // 1. Check Splash Screen (wait for 2s)
        Thread.sleep(2500)

        // 2. Verify Main Menu branding (Logo and Developer Name)
        composeTestRule.onNodeWithContentDescription("App Logo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tunisia Heritage Quest").assertIsDisplayed()
        composeTestRule.onNodeWithText("by Mohamed Rayen Fadhlaoui").assertIsDisplayed()
        composeTestRule.onNodeWithText("Developed by: Mohamed Rayen Fadhlaoui").assertIsDisplayed()

        // 3. Test Navigation: Main Menu -> Category Selection
        composeTestRule.onNodeWithText("Start Quest").performClick()
        composeTestRule.onNodeWithText("Select Category").assertIsDisplayed()

        // 4. Test Navigation: Category -> Difficulty
        composeTestRule.onNodeWithText("Roman Heritage").performClick()
        composeTestRule.onNodeWithText("Select Difficulty").assertIsDisplayed()
        
        // 5. Test Navigation: Difficulty -> Quiz
        composeTestRule.onNodeWithText("Easy").performClick()
        // Verify quiz starts by checking for the question text pattern
        composeTestRule.onNodeWithText("Question 1 / 5").assertExists()
    }
}
