package com.example.mohamedrayen_fadhlaouilabexam

import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.viewmodel.QuizViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        viewModel = QuizViewModel()
    }

    @Test
    fun `test quiz initialization`() {
        // Start quiz with EASY difficulty (should have questions)
        viewModel.startQuiz(null, Difficulty.EASY)
        
        val state = viewModel.uiState.value
        assertTrue(state.questions.isNotEmpty())
        assertEquals(0, state.currentQuestionIndex)
        assertEquals(0, state.score)
        assertEquals(Difficulty.EASY.timerSeconds, state.timeLeft)
    }

    @Test
    fun `test correct answer increases score`() {
        viewModel.startQuiz(null, Difficulty.EASY)
        val initialScore = viewModel.uiState.value.score
        val correctAnswer = viewModel.uiState.value.questions[0].correctAnswerIndex
        
        viewModel.onAnswerSelected(correctAnswer)
        
        assertEquals(initialScore + 10, viewModel.uiState.value.score)
        assertTrue(viewModel.uiState.value.showExplanation)
    }

    @Test
    fun `test wrong answer does not increase score`() {
        viewModel.startQuiz(null, Difficulty.EASY)
        val initialScore = viewModel.uiState.value.score
        val correctAnswer = viewModel.uiState.value.questions[0].correctAnswerIndex
        val wrongAnswer = (correctAnswer + 1) % 4
        
        viewModel.onAnswerSelected(wrongAnswer)
        
        assertEquals(initialScore, viewModel.uiState.value.score)
    }
}
