package com.example.goalapp.ui.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.prefs.PreferenceManager
import com.example.goalapp.notifications.NotificationHelper
import com.example.goalapp.ui.utils.MusicManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit,
    onFeedback: () -> Unit,
    onTerms: () -> Unit,
    onPrivacy: () -> Unit
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    
    var musicEnabled by remember { mutableStateOf(MusicManager.isMusicEnabled(context)) }
    var journalReminderEnabled by remember { mutableStateOf(preferenceManager.isJournalReminderEnabled()) }
    var journalReminderTime by remember { mutableStateOf(preferenceManager.getJournalReminderTime()) }
    var inactivityNotificationsEnabled by remember { mutableStateOf(preferenceManager.isInactivityNotificationEnabled()) }
    
    var themeMode by remember { mutableStateOf(preferenceManager.getThemeMode()) }
    var biometricEnabled by remember { mutableStateOf(preferenceManager.isBiometricEnabled()) }
    var quietHoursEnabled by remember { mutableStateOf(preferenceManager.isQuietHoursEnabled()) }
    
    val userProfile = remember { preferenceManager.getUserProfile() }
    val scrollState = rememberScrollState()

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
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
                .verticalScroll(scrollState)
                .padding(24.dp)
        ) {
            // Account Section
            Text("Account", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            SimpleSettingsItem(
                title = "Profile",
                value = userProfile?.focus?.name?.replace("_", " ") ?: "Guest Mode",
                onClick = {}
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Notifications
            Text("Notifications", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            ToggleItem("Background Music", musicEnabled) {
                musicEnabled = it
                MusicManager.setMusicEnabled(context, it)
            }
            ToggleItem("General Reminders", inactivityNotificationsEnabled) {
                inactivityNotificationsEnabled = it
                preferenceManager.setInactivityNotificationEnabled(it)
            }
            ToggleItem("Quiet Hours", quietHoursEnabled) {
                quietHoursEnabled = it
                preferenceManager.setQuietHoursEnabled(it)
            }
            ToggleItem("Journal Reminder", journalReminderEnabled) {
                journalReminderEnabled = it
                preferenceManager.setJournalReminderEnabled(it)
                if (it) {
                    val parts = journalReminderTime.split(":")
                    NotificationHelper.scheduleJournalReminder(context, parts[0].toInt(), parts[1].toInt())
                } else {
                    NotificationHelper.cancelJournalReminder(context)
                }
            }
            if (journalReminderEnabled) {
                OutlinedButton(onClick = showTimePicker, modifier = Modifier.fillMaxWidth()) {
                    Text("Reminder Time: $journalReminderTime", color = MaterialTheme.colorScheme.onSurface)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Appearance
            Text("Appearance", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            var expanded by remember { mutableStateOf(false) }
            val options = listOf("System", "Light", "Dark")
            SimpleSettingsItem(
                title = "Theme Mode", 
                value = options[themeMode], 
                onClick = { expanded = true }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEachIndexed { index, opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = {
                        themeMode = index
                        preferenceManager.setThemeMode(index)
                        expanded = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Privacy
            Text("Privacy & Security", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            ToggleItem("Biometric Login", biometricEnabled) {
                biometricEnabled = it
                preferenceManager.setBiometricEnabled(it)
            }
            SimpleSettingsItem("Terms of Service", "Read our terms", onClick = onTerms)
            SimpleSettingsItem("Privacy Policy", "Read our policy", onClick = onPrivacy)

            Spacer(modifier = Modifier.height(16.dp))

            // Data
            Text("Data & Storage", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            SimpleSettingsItem("Clear Cache / Reset Data", "Wipe all local progress", onClick = {
                preferenceManager.clearAll()
                onLogout()
            })

            Spacer(modifier = Modifier.height(16.dp))

            // Support
            Text("Support", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            SimpleSettingsItem("Send Feedback", "Help us improve", onClick = { onFeedback() })
            SimpleSettingsItem("App Version", "1.0.0 (MVP)", onClick = {})

            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))

            // Destructive Actions
            SimpleSettingsItem(
                title = "Log Out", 
                value = "Sign out of your account", 
                onClick = onLogout, 
                color = MaterialTheme.colorScheme.error
            )
            SimpleSettingsItem(
                title = "Delete Account", 
                value = "Permanently remove your data", 
                onClick = onDeleteAccount, 
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun SimpleSettingsItem(
    title: String, 
    value: String? = null, 
    onClick: () -> Unit, 
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 12.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = color)
        if (value != null) {
            Text(text = value, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
}

@Composable
fun ToggleItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
}
