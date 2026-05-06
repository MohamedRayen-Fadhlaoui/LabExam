package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MedBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySelectionScreen(
    categoryName: String,
    onDifficultySelected: (Difficulty) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Difficulty") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MedBlue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Category: $categoryName",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MedBlue,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Difficulty.values().forEach { difficulty ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onDifficultySelected(difficulty) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = difficulty.label,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        val hint = when(difficulty) {
                            Difficulty.EASY -> "20s per question - Famous landmarks"
                            Difficulty.MEDIUM -> "15s per question - Historical sites"
                            Difficulty.HARD -> "No timer - Detailed archaeological questions"
                        }
                        Text(text = hint, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}
