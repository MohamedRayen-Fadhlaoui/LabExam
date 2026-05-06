package com.example.mohamedrayen_fadhlaouilabexam

import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QuizViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() = runTest {
        val state = viewModel.uiState.value
        assertFalse(state.isQuizFinished)
        assertEquals(0, state.score)
        assertEquals(0, state.currentQuestionIndex)
    }

    @Test
    fun `starting quiz loads questions and sets timer`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val state = viewModel.uiState.value
        
        assertEquals(5, state.questions.size)
        assertEquals(Difficulty.EASY.timerSeconds, state.timeLeft)
    }

    @Test
    fun `correct answer increases score and shows explanation`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val correctAnswer = viewModel.uiState.value.questions[0].correctAnswerIndex
        
        viewModel.onAnswerSelected(correctAnswer)
        
        val state = viewModel.uiState.value
        assertEquals(10, state.score)
        assertTrue(state.showExplanation)
        assertEquals(correctAnswer, state.selectedAnswerIndex)
    }

    @Test
    fun `wrong answer does not increase score`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val correctAnswer = viewModel.uiState.value.questions[0].correctAnswerIndex
        val wrongAnswer = (correctAnswer + 1) % 4
        
        viewModel.onAnswerSelected(wrongAnswer)
        
        assertEquals(0, viewModel.uiState.value.score)
        assertTrue(viewModel.uiState.value.showExplanation)
    }

    @Test
    fun `next question resets state for new question`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        viewModel.onAnswerSelected(0)
        viewModel.nextQuestion()
        
        val state = viewModel.uiState.value
        assertEquals(1, state.currentQuestionIndex)
        assertFalse(state.showExplanation)
        assertEquals(null, state.selectedAnswerIndex)
    }

    @Test
    fun `finishing quiz sets isQuizFinished to true`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        
        // Answer 5 questions
        repeat(5) {
            viewModel.onAnswerSelected(0)
            viewModel.nextQuestion()
        }
        
        assertTrue(viewModel.uiState.value.isQuizFinished)
    }

    @Test
    fun `pauseTimer stops the countdown`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val initialTime = viewModel.uiState.value.timeLeft
        
        viewModel.pauseTimer()
        advanceTimeBy(2000)
        
        assertEquals(initialTime, viewModel.uiState.value.timeLeft)
    }

    @Test
    fun `resumeTimer restarts the countdown`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val initialTime = viewModel.uiState.value.timeLeft
        
        viewModel.pauseTimer()
        advanceTimeBy(2000)
        assertEquals(initialTime, viewModel.uiState.value.timeLeft)
        
        viewModel.resumeTimer()
        advanceTimeBy(1001)
        
        assertEquals(initialTime - 1, viewModel.uiState.value.timeLeft)
    }

    @Test
    fun `timer decreases timeLeft over time`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val initialTime = viewModel.uiState.value.timeLeft
        
        advanceTimeBy(1001) // Advance slightly more than 1 second
        
        assertEquals(initialTime - 1, viewModel.uiState.value.timeLeft)
    }

    @Test
    fun `timer automatically selects wrong answer when time reaches zero`() = runTest {
        viewModel.startQuiz(null, Difficulty.EASY)
        val timerSeconds = Difficulty.EASY.timerSeconds
        
        advanceTimeBy(timerSeconds * 1000L + 500L) // Advance past the end of the timer
        
        val state = viewModel.uiState.value
        assertEquals(0, state.timeLeft)
        assertTrue(state.showExplanation)
        assertEquals(-1, state.selectedAnswerIndex)
    }
}
