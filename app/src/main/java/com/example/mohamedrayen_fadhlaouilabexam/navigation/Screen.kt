package com.example.mohamedrayen_fadhlaouilabexam.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object MainMenu : Screen("main_menu")
    object CategorySelection : Screen("category_selection")
    object DifficultySelection : Screen("difficulty_selection/{categoryName}") {
        fun createRoute(categoryName: String) = "difficulty_selection/$categoryName"
    }
    object Quiz : Screen("quiz/{categoryName}/{difficultyName}") {
        fun createRoute(categoryName: String, difficultyName: String) = "quiz/$categoryName/$difficultyName"
    }
    object Results : Screen("results/{score}/{total}") {
        fun createRoute(score: Int, total: Int) = "results/$score/$total"
    }
}
