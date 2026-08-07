package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitGoalScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val repository = remember { MainRepository(context) }
    val scope = rememberCoroutineScope()
    
    val habits by repository.getAllHabits().collectAsState(initial = emptyList())
    val goals by repository.getAllGoals().collectAsState(initial = emptyList())
    
    var showAddHabit by remember { mutableStateOf(false) }
    var habitName by remember { mutableStateOf("") }
    var habitFreq by remember { mutableStateOf(Frequency.DAILY) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habits & Goals") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddHabit = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Habit")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Text("Your Habits", style = MaterialTheme.typography.headlineSmall) }
            
            items(habits) { habit ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(habit.name, style = MaterialTheme.typography.titleMedium)
                            Text(habit.frequency.name, style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = {
                            scope.launch {
                                repository.checkInHabit(HabitLog(habitId = habit.id!!))
                            }
                        }) {
                            Icon(Icons.Default.Check, contentDescription = "Check In")
                        }
                    }
                }
            }

            item { Text("Your Goals", style = MaterialTheme.typography.headlineSmall) }
            
            items(goals) { goal ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(goal.name, style = MaterialTheme.typography.titleMedium)
                        LinearProgressIndicator(
                            progress = { goal.currentValue / goal.targetValue },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        )
                        Text("${goal.currentValue} / ${goal.targetValue} ${goal.unit ?: ""}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        if (showAddHabit) {
            AlertDialog(
                onDismissRequest = { showAddHabit = false },
                title = { Text("Add Habit") },
                text = {
                    Column {
                        OutlinedTextField(value = habitName, onValueChange = { habitName = it }, label = { Text("Habit Name") })
                        Spacer(Modifier.height(8.dp))
                        Text("Frequency")
                        Row {
                            Frequency.entries.forEach { freq ->
                                FilterChip(
                                    selected = habitFreq == freq,
                                    onClick = { habitFreq = freq },
                                    label = { Text(freq.name) }
                                )
                                Spacer(Modifier.width(4.dp))
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        scope.launch {
                            repository.addHabit(Habit(name = habitName, frequency = habitFreq))
                            showAddHabit = false
                            habitName = ""
                        }
                    }) { Text("Add") }
                }
            )
        }
    }
}
