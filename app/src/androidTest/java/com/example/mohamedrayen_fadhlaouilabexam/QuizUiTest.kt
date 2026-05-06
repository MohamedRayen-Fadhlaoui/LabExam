package com.example.mohamedrayen_fadhlaouilabexam

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class QuizUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainMenuVisibleAfterSplash() {
        // Since there's a delay in SplashScreen, we might need to wait or just check if it eventually shows
        // For a basic test, let's assume we are on the Main Menu (manually or after splash)
        // In a real scenario, we might skip splash for testing.
        
        // Wait for splash (2s)
        Thread.sleep(3000) 

        composeTestRule.onNodeWithText("Tunisia Heritage Quest").assertExists()
        composeTestRule.onNodeWithText("Start Quest").assertExists()
    }

    @Test
    fun testNavigateToCategorySelection() {
        Thread.sleep(3000)
        
        composeTestRule.onNodeWithText("Start Quest").performClick()
        
        // Check if we are on Category Selection screen
        composeTestRule.onNodeWithText("Select Category").assertExists()
        composeTestRule.onNodeWithText("All Heritage Sites").assertExists()
    }
}
