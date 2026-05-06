package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MedBlue
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.TerraCotta

@Composable
fun ResultsScreen(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    onMainMenu: () -> Unit
) {
    val percentage = if (totalQuestions > 0) (score.toFloat() / (totalQuestions * 10) * 100).toInt() else 0
    
    val performanceMessage = when {
        percentage >= 90 -> "Heritage Expert! 🏛️"
        percentage >= 70 -> "Great Explorer! 🧭"
        percentage >= 50 -> "Curious Traveler 🚶"
        else -> "Beginner Historian 📚"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz Finished!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MedBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your Score",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Text(
            text = "$score / ${totalQuestions * 10}",
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TerraCotta
        )

        Text(
            text = "$percentage%",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = performanceMessage,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MedBlue)
        ) {
            Text("Try Again")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onMainMenu,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Menu")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Developed by: Mohamed Rayen Fadhlaoui",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}
