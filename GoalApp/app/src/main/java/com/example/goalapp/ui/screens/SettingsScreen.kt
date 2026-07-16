package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.goalapp.data.prefs.PreferenceManager
import com.example.goalapp.notifications.NotificationHelper
import com.example.goalapp.ui.utils.MusicManager
import android.app.TimePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    var musicEnabled by remember { mutableStateOf(MusicManager.isMusicEnabled(context)) }
    var journalReminderEnabled by remember { mutableStateOf(preferenceManager.isJournalReminderEnabled()) }
    var journalReminderTime by remember { mutableStateOf(preferenceManager.getJournalReminderTime()) }
    var inactivityNotificationsEnabled by remember { mutableStateOf(preferenceManager.isInactivityNotificationEnabled()) }

    val showTimePicker = {
        val timeParts = journalReminderTime.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        TimePickerDialog(context, { _, h, m ->
            val newTime = String.format("%02d:%02d", h, m)
            journalReminderTime = newTime
            preferenceManager.setJournalReminderTime(newTime)
            if (journalReminderEnabled) {
                NotificationHelper.scheduleJournalReminder(context, h, m)
            }
        }, hour, minute, true).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Music", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = musicEnabled,
                    onCheckedChange = {
                        musicEnabled = it
                        MusicManager.setMusicEnabled(context, it)
                    }
                )
            }
            HorizontalDivider()

            Text(text = "Notifications", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "General Reminders", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = inactivityNotificationsEnabled,
                    onCheckedChange = {
                        inactivityNotificationsEnabled = it
                        preferenceManager.setInactivityNotificationEnabled(it)
                    }
                )
            }
            Text(
                text = "Get notified if you haven't used Goal in a while.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Journal Reminder", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = journalReminderEnabled,
                    onCheckedChange = {
                        journalReminderEnabled = it
                        preferenceManager.setJournalReminderEnabled(it)
                        if (it) {
                            val parts = journalReminderTime.split(":")
                            NotificationHelper.scheduleJournalReminder(context, parts[0].toInt(), parts[1].toInt())
                        } else {
                            NotificationHelper.cancelJournalReminder(context)
                        }
                    }
                )
            }
            
            if (journalReminderEnabled) {
                OutlinedButton(
                    onClick = showTimePicker,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reminder Time: $journalReminderTime")
                }
            }

            HorizontalDivider()

            SettingsItem("Theme", "System default")
            SettingsItem("Account", "Guest Mode")
            SettingsItem("About", "Goal App MVP v1.0")
        }
    }
}

@Composable
fun SettingsItem(title: String, value: String) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
    }
}
