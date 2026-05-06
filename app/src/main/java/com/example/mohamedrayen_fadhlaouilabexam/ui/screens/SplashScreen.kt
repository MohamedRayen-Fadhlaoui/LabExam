package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MedBlue
import kotlinx.coroutines.delay

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.mohamedrayen_fadhlaouilabexam.R

@Composable
fun SplashScreen(onNavigateToMain: () -> Unit) {
    // A simple splash screen that waits for 2 seconds
    LaunchedEffect(key1 = true) {
        delay(2000)
        onNavigateToMain()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MedBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_heritage_logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Tunisia Heritage Quest",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "by Mohamed Rayen Fadhlaoui",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
