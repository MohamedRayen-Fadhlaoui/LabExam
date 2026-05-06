package com.example.mohamedrayen_fadhlaouilabexam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Category
import com.example.mohamedrayen_fadhlaouilabexam.ui.theme.MedBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen(
    onCategorySelected: (Category?) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Category") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MedBlue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Option for All Categories
                item {
                    CategoryItem(name = "All Heritage Sites", onClick = { onCategorySelected(null) })
                }
                
                // Specific categories
                items(Category.values()) { category ->
                    CategoryItem(name = category.displayName, onClick = { onCategorySelected(category) })
                }
            }
        }
    }
}

@Composable
fun CategoryItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}
