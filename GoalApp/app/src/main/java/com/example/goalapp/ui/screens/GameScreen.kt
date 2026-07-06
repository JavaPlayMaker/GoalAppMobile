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
fun GameScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quick Games") },
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
                text = "Pick a 1-minute game",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* coming soon */ }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Deep Breath Challenge", style = MaterialTheme.typography.titleMedium)
                    Text(text = "See how many calm breaths you can take in 60 seconds.", style = MaterialTheme.typography.bodySmall)
                }
            }

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /* coming soon */ }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Gratitude Blitz", style = MaterialTheme.typography.titleMedium)
                    Text(text = "List 5 things you're grateful for as fast as you can.", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Game logic coming soon!",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(onBack = {})
}
