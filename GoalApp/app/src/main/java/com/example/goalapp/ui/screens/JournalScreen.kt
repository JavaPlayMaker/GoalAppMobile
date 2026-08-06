package com.example.goalapp.ui.screens

import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.data.MissionManager
import com.example.goalapp.data.prefs.PreferenceManager
import com.example.goalapp.notifications.NotificationHelper
import com.example.goalapp.data.JournalEntry
import com.example.goalapp.data.LocalBridgeClient
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val preferenceManager = remember { PreferenceManager(context) }
    val missionManager = remember { MissionManager(context) }
    
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var showDatePicker by remember { mutableStateOf(true) }
    var journalText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    
    val selectedDateMillis = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
    val selectedDateKey = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(selectedDateMillis))
    
    // Load entry from server or local when date changes
    LaunchedEffect(selectedDateKey) {
        isLoading = true
        try {
            val response = LocalBridgeClient.getJournalEntry("simulated-user-id", selectedDateKey)
            if (response != null) {
                journalText = response
                preferenceManager.saveJournalLocally(selectedDateKey, response)
            } else {
                journalText = preferenceManager.getLocalJournalEntry(selectedDateKey) ?: ""
            }
        } catch (e: Exception) {
            journalText = preferenceManager.getLocalJournalEntry(selectedDateKey) ?: ""
        } finally {
            isLoading = false
        }
    }

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

                    if (!preferenceManager.isJournalReminderEnabled()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = {
                                preferenceManager.setJournalReminderEnabled(true)
                                val parts = preferenceManager.getJournalReminderTime().split(":")
                                NotificationHelper.scheduleJournalReminder(context, parts[0].toInt(), parts[1].toInt())
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.large
                        ) {
                            Text("Enable Journal Reminders")
                        }
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
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    val wordCount = journalText.split(Regex("\\s+")).filter { it.isNotEmpty() }.size
                    
                    TextField(
                        value = journalText,
                        onValueChange = { 
                            val words = it.split(Regex("\\s+")).filter { s -> s.isNotEmpty() }
                            if (words.size <= 500) {
                                journalText = it 
                            }
                        },
                        label = { Text("How was your day?", color = Color.White.copy(alpha = 0.7f)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 300.dp),
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
                        ),
                        supportingText = {
                            Text(
                                text = "$wordCount / 500 words", 
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    TextButton(
                        onClick = { 
                            preferenceManager.saveJournalLocally(selectedDateKey, journalText)
                            showDatePicker = true 
                        },
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onBackground)
                    ) {
                        Text("Change Date")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                val customerId = "simulated-user-id"
                                // Always save locally first to avoid data loss
                                preferenceManager.saveJournalLocally(selectedDateKey, journalText)
                                
                                val result = LocalBridgeClient.saveJournalEntry(
                                    JournalEntry(
                                        customer_id = customerId,
                                        content = journalText,
                                        entry_date = selectedDateKey
                                    )
                                )
                                
                                if (result.isSuccess) {
                                    missionManager.completeJournalMission()
                                    onBack()
                                } else {
                                    // Handle failure - it's already saved locally
                                    Log.e("JournalScreen", "Failed to save journal to server: ${result.exceptionOrNull()?.message}")
                                    missionManager.completeJournalMission()
                                    onBack()
                                }
                                isLoading = false
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            Text("Done")
                        }
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
