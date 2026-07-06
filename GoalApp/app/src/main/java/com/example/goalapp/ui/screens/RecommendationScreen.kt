package com.example.goalapp.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.data.GoalActivity
import com.example.goalapp.data.Mood
import com.example.goalapp.data.EnergyLevel
import com.example.goalapp.data.SocialPreference
import com.example.goalapp.data.UserCheckIn
import com.example.goalapp.data.UserProfile
import com.example.goalapp.data.ActivityRepository
import com.example.goalapp.data.TimeAvailable
import com.example.goalapp.ui.components.LoadingOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecommendationScreen(
    checkIn: UserCheckIn,
    profile: UserProfile,
    onDone: () -> Unit
) {
    // State to hold the current recommendation so it doesn't change on every recomposition
    var currentActivity by remember { 
        mutableStateOf(ActivityRepository.getRecommendation(checkIn, profile)) 
    }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Suggestion") },
                navigationIcon = {
                    IconButton(onClick = onDone) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            AnimatedContent(
                targetState = currentActivity,
                transitionSpec = {
                    fadeIn().togetherWith(fadeOut())
                },
                label = "ActivityTransition"
            ) { targetActivity ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "You should try to:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = targetActivity.name,
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "Why it fits",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = targetActivity.whyFits,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "First Step",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = targetActivity.firstStep,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            onClick = onDone,
                            modifier = Modifier.fillMaxWidth().height(56.dp)
                        ) {
                            Text("Done")
                        }
                        OutlinedButton(
                            onClick = {
                                scope.launch {
                                    isLoading = true
                                    delay(800) // Thinking time - hides the state change
                                    currentActivity = ActivityRepository.getRecommendation(checkIn, profile)
                                    isLoading = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(56.dp)
                        ) {
                            Text("Try Another Idea")
                        }
                    }
                }
            }
            
            if (isLoading) {
                LoadingOverlay()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationScreenPreview() {
    RecommendationScreen(
        checkIn = UserCheckIn(
            mood = Mood.TIRED,
            energyLevel = EnergyLevel.LOW,
            socialPreference = SocialPreference.EITHER,
            timeAvailable = TimeAvailable.THIRTY_MINUTES
        ),
        profile = UserProfile(),
        onDone = {}
    )
}
