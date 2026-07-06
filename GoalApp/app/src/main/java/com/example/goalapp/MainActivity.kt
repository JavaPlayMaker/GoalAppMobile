package com.example.goalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.*
import com.example.goalapp.data.UserCheckIn
import com.example.goalapp.data.UserProfile
import com.example.goalapp.ui.screens.*
import com.example.goalapp.ui.theme.GoalAppTheme

/**
 * MainActivity is the entry point of the Goal app.
 * It sets up the navigation and applies the app theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoalAppTheme {
                GoalAppNavigation()
            }
        }
    }
}

/**
 * GoalAppNavigation defines the navigation routes and screen transitions for the MVP.
 */
@Composable
fun GoalAppNavigation() {
    val navController = rememberNavController()
    var lastCheckIn by remember { mutableStateOf<UserCheckIn?>(null) }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onNavigateToHome = {
                    navController.navigate("profile_setup") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("profile_setup") {
            ProfileSetupScreen(
                onComplete = { profile ->
                    userProfile = profile
                    navController.navigate("home") {
                        popUpTo("profile_setup") { inclusive = true }
                    }
                }
            )
        }
        
        composable("home") {
            HomeScreen(
                onLearn = { navController.navigate("learn") },
                onGoal = { navController.navigate("checkin") },
                onGame = { navController.navigate("game") },
                onJournal = { navController.navigate("journal") },
                onSettings = { navController.navigate("settings") }
            )
        }
        
        composable("learn") {
            LearnScreen(onBack = { navController.popBackStack() })
        }

        composable("game") {
            GameScreen(onBack = { navController.popBackStack() })
        }

        composable("journal") {
            JournalScreen(onBack = { navController.popBackStack() })
        }
        
        composable("checkin") {
            CheckInScreen(
                onRecommendation = { checkIn ->
                    lastCheckIn = checkIn
                    navController.navigate("recommendation")
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("recommendation") {
            lastCheckIn?.let { checkIn ->
                RecommendationScreen(
                    checkIn = checkIn,
                    profile = userProfile ?: UserProfile(),
                    onDone = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
            }
        }
        
        composable("settings") {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
