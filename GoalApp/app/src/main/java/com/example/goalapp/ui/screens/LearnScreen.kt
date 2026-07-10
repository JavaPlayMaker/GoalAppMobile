package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learn") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What would you like to explore?",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* coming soon */ }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "The Art of Being Alone", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Solitude is a strength. It's a time to recharge, reflect, and discover yourself without external noise. Learning to be comfortable in your own company is the first step toward genuine confidence.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* coming soon */ }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "How to Deal With It", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Practical strategies for managing difficult moments.", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Detailed lessons coming soon!",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearnScreenPreview() {
    LearnScreen(onBack = {})
}
