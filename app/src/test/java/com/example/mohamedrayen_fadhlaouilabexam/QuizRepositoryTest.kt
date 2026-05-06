package com.example.mohamedrayen_fadhlaouilabexam

import com.example.mohamedrayen_fadhlaouilabexam.data.repository.QuizRepository
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Category
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class QuizRepositoryTest {

    private lateinit var repository: QuizRepository

    @Before
    fun setup() {
        repository = QuizRepository()
    }

    @Test
    fun `getQuestions returns correct number of questions`() {
        val questions = repository.getQuestions(Category.ROMAN, Difficulty.EASY)
        assertEquals(5, questions.size)
    }

    @Test
    fun `getQuestions returns questions of specified category`() {
        val questions = repository.getQuestions(Category.ISLAMIC, Difficulty.EASY)
        assertTrue(questions.all { it.category == Category.ISLAMIC })
    }

    @Test
    fun `getQuestions returns questions of specified difficulty`() {
        val questions = repository.getQuestions(null, Difficulty.HARD)
        assertTrue(questions.all { it.difficulty == Difficulty.HARD })
    }

    @Test
    fun `getQuestions for all categories returns mixed results`() {
        val questions = repository.getQuestions(null, Difficulty.MEDIUM)
        assertEquals(5, questions.size)
        assertTrue(questions.all { it.difficulty == Difficulty.MEDIUM })
    }
}
