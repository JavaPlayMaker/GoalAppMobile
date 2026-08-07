package com.example.goalapp.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.data.Mood
import com.example.goalapp.data.EnergyLevel
import com.example.goalapp.data.SocialPreference
import com.example.goalapp.data.UserCheckIn
import com.example.goalapp.data.UserProfile
import com.example.goalapp.data.GoalActivity
import com.example.goalapp.data.TimeAvailable
import com.example.goalapp.data.MainRepository
import com.example.goalapp.data.ActivityLog
import com.example.goalapp.ui.components.LoadingOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecommendationScreen(
    checkIn: UserCheckIn,
    profile: UserProfile,
    onDone: () -> Unit
) {
    val context = LocalContext.current
    val repository = remember { MainRepository(context) }
    var currentActivity by remember { mutableStateOf<GoalActivity?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(checkIn, profile) {
        currentActivity = repository.getSuggestedGoal(checkIn)
        isLoading = false
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Your Goal", color = MaterialTheme.colorScheme.onBackground) },
                navigationIcon = {
                    IconButton(onClick = onDone) {
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
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            currentActivity?.let { activity ->
                AnimatedContent(
                    targetState = activity,
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
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = targetActivity.name,
                                style = MaterialTheme.typography.displaySmall,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        RecommendationCard(title = "Why it fits", content = targetActivity.whyFits)
                        RecommendationCard(title = "First Step", content = targetActivity.firstStep)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        isLoading = true
                                        // Log the activity and award points
                                        repository.logActivity(
                                            ActivityLog(
                                                name = targetActivity.name,
                                                category = targetActivity.category,
                                                startTime = System.currentTimeMillis(),
                                                endTime = System.currentTimeMillis(), // Instant for MVP
                                                moodBefore = checkIn.mood,
                                                pointsEarned = 50
                                            )
                                        )
                                        Toast.makeText(context, "Completed! +50 points", Toast.LENGTH_SHORT).show()
                                        onDone()
                                    }
                                },
                                modifier = Modifier.fillMaxWidth().height(56.dp),
                                shape = MaterialTheme.shapes.large,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Mark as Done (+50 pts)")
                            }
                            
                            OutlinedButton(
                                onClick = {
                                    scope.launch {
                                        isLoading = true
                                        delay(500)
                                        currentActivity = repository.getSuggestedGoal(checkIn)
                                        isLoading = false
                                    }
                                },
                                modifier = Modifier.fillMaxWidth().height(56.dp),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Text("Try Another Idea")
                            }
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

@Composable
fun RecommendationCard(title: String, content: String) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(20.dp).fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )
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
