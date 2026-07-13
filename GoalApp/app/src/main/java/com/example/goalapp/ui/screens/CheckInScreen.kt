package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInScreen(
    onRecommendation: (UserCheckIn) -> Unit,
    onBack: () -> Unit
) {
    var mood by remember { mutableStateOf(Mood.JUST_WANT_SOMETHING_TO_DO) }
    var energy by remember { mutableStateOf(EnergyLevel.MEDIUM) }
    var social by remember { mutableStateOf(SocialPreference.EITHER) }
    var time by remember { mutableStateOf(TimeAvailable.THIRTY_MINUTES) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Check-in", color = MaterialTheme.colorScheme.onBackground) },
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            QuestionSection("How are you feeling?", Mood.entries, mood) { mood = it as Mood }
            QuestionSection("Energy level?", EnergyLevel.entries, energy) { energy = it as EnergyLevel }
            QuestionSection("Social preference?", SocialPreference.entries, social) { social = it as SocialPreference }
            QuestionSection("Time available?", TimeAvailable.entries, time) { time = it as TimeAvailable }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onRecommendation(UserCheckIn(mood, energy, social, time))
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text("Get Suggestion")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionSection(
    title: String,
    options: List<Any>,
    selected: Any,
    onSelect: (Any) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                val label = when (option) {
                    is Mood -> option.displayName
                    is EnergyLevel -> option.displayName
                    is SocialPreference -> option.displayName
                    is TimeAvailable -> option.displayName
                    else -> option.toString()
                }
                
                val isSelected = option == selected
                
                Button(
                    onClick = { onSelect(option) },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                        contentColor = if (isSelected) Color.White else Color(0xFF2D2D2D)
                    ),
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(label)
                }
            }
        }
    }
}
