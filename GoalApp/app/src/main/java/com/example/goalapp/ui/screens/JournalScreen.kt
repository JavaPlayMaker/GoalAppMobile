package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(onBack: () -> Unit) {
    // State for the calendar
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var showDatePicker by remember { mutableStateOf(true) }
    var journalText by remember { mutableStateOf("") }
    
    // Simple in-memory storage (Replace with Room/Database later)
    val journalEntries = remember { mutableStateMapOf<Long, String>() }

    val selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
    
    // Load entry if date changes
    LaunchedEffect(selectedDate) {
        journalText = journalEntries[selectedDate] ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Journal") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (!showDatePicker) {
                        Button(
                            onClick = { 
                                journalEntries[selectedDate] = journalText
                                showDatePicker = true 
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text("Save")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showDatePicker) {
                Text(
                    text = "Select a date to write",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Built-in Material 3 Calendar
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { showDatePicker = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Write for ${formatDate(selectedDate)}")
                }
            } else {
                Text(
                    text = formatDate(selectedDate),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Word count logic
                val wordCount = journalText.split(Regex("\\s+")).filter { it.isNotEmpty() }.size
                
                OutlinedTextField(
                    value = journalText,
                    onValueChange = { 
                        val words = it.split(Regex("\\s+")).filter { s -> s.isNotEmpty() }
                        if (words.size <= 500) {
                            journalText = it 
                        }
                    },
                    label = { Text("How was your day?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp),
                    placeholder = { Text("Write your thoughts here...") },
                    supportingText = {
                        Text(
                            text = "$wordCount / 500 words", 
                            color = if (wordCount >= 500) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(onClick = { showDatePicker = true }) {
                    Text("Change Date")
                }
            }
        }
    }
}

private fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun JournalScreenPreview() {
    JournalScreen(onBack = {})
}
