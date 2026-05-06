package com.example.mohamedrayen_fadhlaouilabexam.domain.model

data class Question(
    val id: Int,
    val text: String,
    val imageResId: Int?,
    val choices: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String,
    val category: Category,
    val difficulty: Difficulty
)
