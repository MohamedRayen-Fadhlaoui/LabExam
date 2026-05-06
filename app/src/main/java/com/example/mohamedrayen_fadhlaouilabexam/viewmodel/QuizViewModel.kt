package com.example.mohamedrayen_fadhlaouilabexam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohamedrayen_fadhlaouilabexam.data.repository.QuizRepository
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Category
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val timeLeft: Int = 0,
    val isQuizFinished: Boolean = false,
    val selectedAnswerIndex: Int? = null,
    val showExplanation: Boolean = false,
    val isPaused: Boolean = false
)

class QuizViewModel : ViewModel() {
    private val repository = QuizRepository()
    
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun startQuiz(category: Category?, difficulty: Difficulty) {
        val questions = repository.getQuestions(category, difficulty)
        _uiState.value = QuizUiState(
            questions = questions,
            timeLeft = difficulty.timerSeconds
        )
        if (difficulty.timerSeconds > 0) {
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeft > 0 && !_uiState.value.showExplanation) {
                if (!_uiState.value.isPaused) {
                    delay(1000)
                    _uiState.value = _uiState.value.copy(timeLeft = _uiState.value.timeLeft - 1)
                } else {
                    delay(100) // Small delay while paused
                }
            }
            if (_uiState.value.timeLeft == 0 && !_uiState.value.showExplanation) {
                onAnswerSelected(-1) // Time's up
            }
        }
    }

    fun onAnswerSelected(index: Int) {
        timerJob?.cancel()
        val currentState = _uiState.value
        val currentQuestion = currentState.questions[currentState.currentQuestionIndex]
        
        val isCorrect = index == currentQuestion.correctAnswerIndex
        val newScore = if (isCorrect) currentState.score + 10 else currentState.score
        
        _uiState.value = currentState.copy(
            selectedAnswerIndex = index,
            score = newScore,
            showExplanation = true
        )
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex < currentState.questions.size - 1) {
            _uiState.value = currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswerIndex = null,
                showExplanation = false,
                timeLeft = currentState.questions[0].difficulty.timerSeconds
            )
            if (currentState.questions[0].difficulty.timerSeconds > 0) {
                startTimer()
            }
        } else {
            _uiState.value = currentState.copy(isQuizFinished = true)
        }
    }

    fun pauseTimer() {
        _uiState.value = _uiState.value.copy(isPaused = true)
    }

    fun resumeTimer() {
        _uiState.value = _uiState.value.copy(isPaused = false)
    }
}
