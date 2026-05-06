package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Question
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.*
import com.example.mohamedrayen_fadhlaouilabexam.viewmodel.QuizViewModel

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    onQuizFinished: (Int, Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    if (uiState.isQuizFinished) {
        onQuizFinished(uiState.score, uiState.questions.size)
    }

    if (uiState.questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No questions found for this selection.")
        }
        return
    }

    val currentQuestion = uiState.questions[uiState.currentQuestionIndex]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Question ${uiState.currentQuestionIndex + 1}/${uiState.questions.size}") },
                actions = {
                    if (uiState.timeLeft > 0) {
                        Text(
                            text = "${uiState.timeLeft}s",
                            modifier = Modifier.padding(end = 16.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = if (uiState.timeLeft < 5) Color.Red else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MedBlue, titleContentColor = White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image Placeholder/Loader
            if (currentQuestion.imageResId != null) {
                AsyncImage(
                    model = currentQuestion.imageResId,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Gray),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = currentQuestion.text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Choices
            currentQuestion.choices.forEachIndexed { index, choice ->
                ChoiceButton(
                    text = choice,
                    isSelected = uiState.selectedAnswerIndex == index,
                    isCorrect = currentQuestion.correctAnswerIndex == index,
                    showResult = uiState.showExplanation,
                    onClick = {
                        if (!uiState.showExplanation) {
                            viewModel.onAnswerSelected(index)
                        }
                    }
                )
            }

            if (uiState.showExplanation) {
                Spacer(modifier = Modifier.weight(1f))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Beige)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = if (uiState.selectedAnswerIndex == currentQuestion.correctAnswerIndex) "Correct!" else "Wrong!",
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.selectedAnswerIndex == currentQuestion.correctAnswerIndex) Olive else TerraCotta
                        )
                        Text(text = currentQuestion.explanation, fontSize = 14.sp)
                        
                        Button(
                            onClick = { viewModel.nextQuestion() },
                            modifier = Modifier.align(Alignment.End).padding(top = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MedBlue)
                        ) {
                            Text("Next")
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ChoiceButton(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showResult: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        showResult && isCorrect -> Olive.copy(alpha = 0.2f)
        showResult && isSelected && !isCorrect -> TerraCotta.copy(alpha = 0.2f)
        isSelected -> LightMedBlue.copy(alpha = 0.3f)
        else -> Color.Transparent
    }

    val borderColor = when {
        showResult && isCorrect -> Olive
        showResult && isSelected && !isCorrect -> TerraCotta
        isSelected -> MedBlue
        else -> Gray
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = if (showResult && isCorrect) Olive else if (showResult && isSelected && !isCorrect) TerraCotta else Black
        )
    }
}
