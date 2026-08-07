package com.example.goalapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.ActivityLog
import com.example.goalapp.data.MainRepository
import com.example.goalapp.data.Mood
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityLogScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val repository = remember { MainRepository(context) }
    val scope = rememberCoroutineScope()
    
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Work") }
    var selectedMoodBefore by remember { mutableStateOf<Mood?>(null) }
    
    val categories = listOf("Work", "Exercise", "Sleep", "Studying", "Socializing", "Commuting", "Hobbies", "Other")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log Activity") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("What did you do?") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text("Category", style = MaterialTheme.typography.titleMedium)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        FilterChip(
                            selected = category == cat,
                            onClick = { category = cat },
                            label = { Text(cat) }
                        )
                    }
                }
            }

            item {
                Text("How were you feeling before?", style = MaterialTheme.typography.titleMedium)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Mood.entries.forEach { mood ->
                        FilterChip(
                            selected = selectedMoodBefore == mood,
                            onClick = { selectedMoodBefore = mood },
                            label = { Text(mood.displayName) }
                        )
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        scope.launch {
                            repository.logActivity(
                                ActivityLog(
                                    name = name,
                                    category = category,
                                    startTime = System.currentTimeMillis(),
                                    moodBefore = selectedMoodBefore
                                )
                            )
                            onBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = name.isNotBlank()
                ) {
                    Text("Save Activity")
                }
            }
        }
    }
}
