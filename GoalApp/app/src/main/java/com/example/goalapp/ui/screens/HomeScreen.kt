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
    onHistory: () -> Unit
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
                    .padding(bottom = 16.dp)
                    .clickable { onDailyMissions() }
            )

            Text(
                text = "Welcome",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HomeButton(
                label = "History",
                action = onHistory,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .height(56.dp)
            )

            // 2x2 Grid Layout
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeButton(label = "Learn", action = onLearn)
                    HomeButton(label = "Game", action = onGame)
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeButton(label = "Goal", action = onGoal)
                    HomeButton(label = "Journal", action = onJournal)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
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
            onHistory = {}
        )
    }
}
