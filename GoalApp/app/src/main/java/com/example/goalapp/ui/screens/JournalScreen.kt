package com.example.goalapp.ui.screens

import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.data.JournalEntry
import com.example.goalapp.data.MainRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = remember { MainRepository(context) }
    
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var showDatePicker by remember { mutableStateOf(true) }
    var journalText by remember { mutableStateOf("") }
    var selectedTemplate by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    
    val selectedDateMillis = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
    
    val templates = listOf(
        "Standard" to "Write anything...",
        "Gratitude" to "Today I am grateful for...\n1.\n2.\n3.",
        "Reflection" to "What went well today?\nWhat could be improved?",
        "Morning" to "My main goal for today is...\nI want to feel...",
        "Deep Work" to "What did I accomplish during my focus session?"
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Journal", color = MaterialTheme.colorScheme.onBackground) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showDatePicker) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Select a date to write",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false,
                        modifier = Modifier.fillMaxWidth(),
                        colors = DatePickerDefaults.colors(
                            containerColor = Color.Transparent,
                            titleContentColor = MaterialTheme.colorScheme.onBackground,
                            headlineContentColor = MaterialTheme.colorScheme.onBackground,
                            selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                            selectedDayContentColor = Color.White,
                            todayContentColor = MaterialTheme.colorScheme.primary,
                            todayDateBorderColor = MaterialTheme.colorScheme.primary,
                            dayContentColor = MaterialTheme.colorScheme.onBackground,
                            weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                            navigationContentColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = { showDatePicker = false },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Write for ${formatDate(selectedDateMillis)}")
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = formatDate(selectedDateMillis),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Templates Row
                    Text(
                        text = "Choose a template:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(templates) { (name, content) ->
                            FilterChip(
                                selected = selectedTemplate == name,
                                onClick = { 
                                    selectedTemplate = name
                                    journalText = content
                                },
                                label = { Text(name) }
                            )
                        }
                    }
                    
                    Box(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = journalText,
                            onValueChange = { journalText = it },
                            label = { Text("How was your day?", color = Color.White.copy(alpha = 0.7f)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 350.dp),
                            placeholder = { Text("Write your thoughts here...", color = Color.White.copy(alpha = 0.5f)) },
                            shape = MaterialTheme.shapes.large,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = MaterialTheme.colorScheme.primary,
                                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.White
                            )
                        )

                        // Photo Button
                        IconButton(
                            onClick = { /* Action to pick photo */ },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.shapes.medium)
                        ) {
                            Icon(
                                Icons.Default.AddPhotoAlternate, 
                                contentDescription = "Add Photo",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                repository.saveJournalEntry(
                                    JournalEntry(
                                        content = journalText,
                                        created_at = selectedDateMillis,
                                        templateType = selectedTemplate
                                    )
                                )
                                onBack()
                                isLoading = false
                            }
                        },
                        enabled = !isLoading && journalText.isNotBlank(),
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                        } else {
                            Text("Save Entry (+10 pts)")
                        }
                    }

                    TextButton(
                        onClick = { showDatePicker = true },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Back to Date Selection", color = MaterialTheme.colorScheme.onBackground)
                    }
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
