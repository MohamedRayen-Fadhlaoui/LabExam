package com.example.mohamedrayen_fadhlaouilabexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Category
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.navigation.Screen
import com.example.mohamedrayen_fadhlaouilabexam.ui.screens.*
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MohamedRayen_FadhlaouiLabExamTheme
import com.example.mohamedrayen_fadhlaouilabexam.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    
    // We'll keep a reference to the viewmodel to handle lifecycle events
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MohamedRayen_FadhlaouiLabExamTheme {
                // Initialize ViewModel here or inside the navigation graph
                quizViewModel = viewModel()
                
                TunisiaHeritageQuestApp(quizViewModel)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // If the app goes to background, pause the timer
        if (::quizViewModel.isInitialized) {
            quizViewModel.pauseTimer()
        }
    }

    override fun onResume() {
        super.onResume()
        // Resume when user comes back
        if (::quizViewModel.isInitialized) {
            quizViewModel.resumeTimer()
        }
    }
}

@Composable
fun TunisiaHeritageQuestApp(quizViewModel: QuizViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToMain = {
                navController.navigate(Screen.MainMenu.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.MainMenu.route) {
            MainMenuScreen(
                onStartGame = { navController.navigate(Screen.CategorySelection.route) },
                onViewAbout = { /* Could add an About screen if needed */ }
            )
        }

        composable(Screen.CategorySelection.route) {
            CategorySelectionScreen(
                onCategorySelected = { category ->
                    val catName = category?.name ?: "ALL"
                    navController.navigate(Screen.DifficultySelection.createRoute(catName))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.DifficultySelection.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "ALL"
            DifficultySelectionScreen(
                categoryName = categoryName,
                onDifficultySelected = { difficulty ->
                    navController.navigate(Screen.Quiz.createRoute(categoryName, difficulty.name))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Quiz.route,
            arguments = listOf(
                navArgument("categoryName") { type = NavType.StringType },
                navArgument("difficultyName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            val difficultyName = backStackEntry.arguments?.getString("difficultyName") ?: Difficulty.EASY.name
            
            val category = if (categoryName == "ALL") null else Category.valueOf(categoryName!!)
            val difficulty = Difficulty.valueOf(difficultyName)

            // Start the quiz once when entering this screen
            remember {
                quizViewModel.startQuiz(category, difficulty)
                true
            }

            QuizScreen(
                viewModel = quizViewModel,
                onQuizFinished = { score, total ->
                    navController.navigate(Screen.Results.createRoute(score, total)) {
                        popUpTo(Screen.MainMenu.route)
                    }
                }
            )
        }

        composable(
            route = Screen.Results.route,
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val total = backStackEntry.arguments?.getInt("total") ?: 0
            
            ResultsScreen(
                score = score,
                totalQuestions = total,
                onRestart = {
                    navController.popBackStack(Screen.CategorySelection.route, false)
                },
                onMainMenu = {
                    navController.navigate(Screen.MainMenu.route) {
                        popUpTo(Screen.MainMenu.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
