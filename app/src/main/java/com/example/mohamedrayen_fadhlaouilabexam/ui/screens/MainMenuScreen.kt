package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MedBlue
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.TerraCotta

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.mohamedrayen_fadhlaouilabexam.R

@Composable
fun MainMenuScreen(
    onStartGame: () -> Unit,
    onViewAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_heritage_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Tunisia Heritage Quest",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MedBlue,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "by Mohamed Rayen Fadhlaoui",
            fontSize = 16.sp,
            color = TerraCotta,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onStartGame,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MedBlue)
        ) {
            Text("Start Quest", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onViewAbout,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TerraCotta)
        ) {
            Text("About Heritage", fontSize = 18.sp)
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
