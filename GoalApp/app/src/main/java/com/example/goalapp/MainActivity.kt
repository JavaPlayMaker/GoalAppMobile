package com.example.goalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.compose.ui.platform.LocalContext
import com.example.goalapp.data.EnergyLevel
import com.example.goalapp.data.Mood
import com.example.goalapp.data.SocialPreference
import com.example.goalapp.data.TimeAvailable
import com.example.goalapp.data.UserCheckIn
import com.example.goalapp.data.UserProfile
import com.example.goalapp.data.prefs.PreferenceManager
import com.example.goalapp.notifications.NotificationHelper
import com.example.goalapp.ui.screens.*
import com.example.goalapp.ui.theme.GoalAppTheme

/**
 * MainActivity is the entry point of the Goal app.
 * It sets up the navigation and applies the app theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper.createNotificationChannel(this)
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
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    val navController = rememberNavController()
    var lastCheckIn by remember { mutableStateOf<UserCheckIn?>(null) }
    var userProfile by remember { mutableStateOf(preferenceManager.getUserProfile()) }

    val handleDone: (() -> Unit) -> Unit = { onNoTrigger ->
        preferenceManager.incrementDoneCount()
        if (preferenceManager.getDoneCount() >= 3) {
            navController.navigate("feedback")
        } else {
            onNoTrigger()
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (userProfile != null) "home" else "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onContinueGuest = {
                    if (preferenceManager.isProfileCompleted()) {
                        navController.navigate("home") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } else {
                        navController.navigate("profile_setup") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                },
                onCreateAccount = {
                    navController.navigate("auth")
                }
            )
        }

        composable("auth") {
            AuthScreen(
                onEmailSent = { email ->
                    navController.navigate("verify/$email")
                },
                onGoogleSignIn = {
                    // Simulated Google Sign In
                    if (preferenceManager.isProfileCompleted()) {
                        navController.navigate("home") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } else {
                        navController.navigate("profile_setup") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            "verify/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            EmailVerificationScreen(
                email = email,
                onVerified = {
                    if (preferenceManager.isProfileCompleted()) {
                        navController.navigate("home") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } else {
                        navController.navigate("profile_setup") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                },
                onResend = { /* Logic to resend email */ }
            )
        }

        composable("profile_setup") {
            ProfileSetupScreen(
                onComplete = { profile ->
                    preferenceManager.saveUserProfile(profile)
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
                onGoal = { 
                    lastCheckIn = UserCheckIn(
                        mood = Mood.JUST_WANT_SOMETHING_TO_DO,
                        energyLevel = EnergyLevel.MEDIUM,
                        socialPreference = SocialPreference.EITHER,
                        timeAvailable = TimeAvailable.THIRTY_MINUTES
                    )
                    navController.navigate("recommendation") 
                },
                onGame = { navController.navigate("game") },
                onJournal = { navController.navigate("journal") },
                onSettings = { navController.navigate("settings") },
                onDailyMissions = { navController.navigate("daily_missions") },
                onMyStats = { navController.navigate("my_stats") }
            )
        }
        
        composable("daily_missions") {
            DailyMissionsScreen(onBack = { navController.popBackStack() })
        }

        composable("my_stats") {
            MyStatsScreen(onBack = { navController.popBackStack() })
        }
        
        composable("learn") {
            LearnScreen(
                onBack = {
                    if (navController.previousBackStackEntry != null && 
                        navController.previousBackStackEntry?.destination?.route != "home") {
                        navController.popBackStack()
                    } else {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                },
                onDone = {
                    handleDone {
                        // Stay in LearnScreen (it handles its own state change internally)
                    }
                }
            )
        }

        composable("game") {
            GameScreen(
                onBack = {
                    if (navController.previousBackStackEntry != null && 
                        navController.previousBackStackEntry?.destination?.route != "home") {
                        navController.popBackStack()
                    } else {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                },
                onDone = {
                    handleDone {
                        // Stay in GameScreen
                    }
                }
            )
        }

        composable("journal") {
            JournalScreen(onBack = { 
                handleDone {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                    }
                }
            })
        }
        
        composable("recommendation") {
            lastCheckIn?.let { checkIn ->
                RecommendationScreen(
                    checkIn = checkIn,
                    profile = userProfile ?: UserProfile(),
                    onDone = {
                        handleDone {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = false }
                            }
                        }
                    }
                )
            }
        }
        
        composable("settings") {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable("feedback") {
            FeedbackScreen(
                onDismiss = {
                    preferenceManager.resetDoneCount()
                    navController.navigate("home") {
                        popUpTo("feedback") { inclusive = true }
                    }
                }
            )
        }
    }
}
