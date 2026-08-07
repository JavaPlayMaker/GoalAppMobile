package com.example.goalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import com.example.goalapp.R
import com.example.goalapp.data.UserStats

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.goalapp.ui.utils.MusicManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userStats: UserStats,
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
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Goal", color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(12.dp))
                        // Points Badge
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = CircleShape
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Stars, 
                                    contentDescription = null, 
                                    modifier = Modifier.size(16.dp), 
                                    tint = Color(0xFFFFD700)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${userStats.total_points}",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top Content (Logo & Missions)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "App Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(160.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shadow(4.dp, RoundedCornerShape(24.dp))
                            .clickable { onDailyMissions() }
                    )
                    // Level indicator
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape,
                        modifier = Modifier.offset(x = (-8).dp, y = (8).dp) // Adjusted offset
                    ) {
                        Text(
                            text = "Lvl ${userStats.level}",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tap above for Daily Missions!",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            // Central Action: Goal Circle
            Surface(
                onClick = onGoal,
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.Center),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                tonalElevation = 8.dp,
                shadowElevation = 12.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "GOAL",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )
                }
            }

            // Bottom Menu Bar
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MenuIconButton(
                        label = "Learn",
                        icon = Icons.Default.School,
                        onClick = onLearn,
                        locked = !userStats.unlocked_learn,
                        pointsNeeded = 200
                    )
                    MenuIconButton(
                        label = "Game",
                        icon = Icons.Default.SportsEsports,
                        onClick = onGame,
                        locked = !userStats.unlocked_games,
                        pointsNeeded = 500
                    )
                    MenuIconButton(
                        label = "Journal",
                        icon = Icons.Default.EditNote,
                        onClick = onJournal
                    )
                    MenuIconButton(
                        label = "Stats",
                        icon = Icons.Default.BarChart,
                        onClick = onMyStats
                    )
                }
            }
        }
    }
}

@Composable
fun MenuIconButton(
    label: String, 
    icon: ImageVector, 
    onClick: () -> Unit,
    locked: Boolean = false,
    pointsNeeded: Int = 0
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(enabled = !locked, onClick = onClick)
            .padding(horizontal = 8.dp)
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (locked) Color.Gray else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            if (locked) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked",
                    tint = Color.Red,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
        Text(
            text = if (locked) "${pointsNeeded}pts" else label,
            style = MaterialTheme.typography.labelSmall,
            color = if (locked) Color.Gray else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    com.example.goalapp.ui.theme.GoalAppTheme {
        HomeScreen(
            userStats = UserStats(total_points = 350, unlocked_learn = true),
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
