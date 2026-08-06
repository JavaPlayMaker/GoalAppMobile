package com.example.goalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.R

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.goalapp.ui.utils.MusicManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLearn: () -> Unit,
    onGoal: () -> Unit,
    onGame: () -> Unit,
    onJournal: () -> Unit,
    onSettings: () -> Unit,
    onDailyMissions: () -> Unit,
    onMyStats: () -> Unit
) {
    val context = LocalContext.current
    
    DisposableEffect(Unit) {
        if (MusicManager.isMusicEnabled(context)) {
            MusicManager.start(context)
        }
        onDispose {
            MusicManager.stop()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Goal", color = MaterialTheme.colorScheme.onBackground) },
                actions = {
                    IconButton(onClick = onSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onBackground)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp)
                    .clickable { onDailyMissions() }
            )

            Text(
                text = "Tap above for Daily Missions!",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Primary Action: Goal (Prominent visibility for the app's main function)
            HomeButton(
                label = "Goal",
                action = onGoal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Secondary Actions Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeButton(
                    label = "Learn",
                    action = onLearn,
                    modifier = Modifier.weight(1f).height(90.dp)
                )
                HomeButton(
                    label = "Game",
                    action = onGame,
                    modifier = Modifier.weight(1f).height(90.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeButton(
                    label = "Journal",
                    action = onJournal,
                    modifier = Modifier.weight(1f).height(90.dp)
                )
                HomeButton(
                    label = "My Stats",
                    action = onMyStats,
                    modifier = Modifier.weight(1f).height(90.dp)
                )
            }

        }
    }
}

@Composable
fun HomeButton(label: String, action: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .then(if (modifier == Modifier) Modifier.height(100.dp) else Modifier),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    com.example.goalapp.ui.theme.GoalAppTheme {
        HomeScreen(
            onLearn = {},
            onGoal = {},
            onGame = {},
            onJournal = {},
            onSettings = {},
            onDailyMissions = {},
            onMyStats = {}
        )
    }
}
