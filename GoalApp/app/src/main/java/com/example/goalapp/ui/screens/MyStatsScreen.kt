package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.MissionManager
import com.example.goalapp.data.StatsEngine
import com.example.goalapp.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStatsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val missionManager = remember { MissionManager(context) }
    val statsEngine = remember { StatsEngine() }
    
    // Ensure missions are reset if a new day has started
    LaunchedEffect(Unit) {
        missionManager.checkAndResetMissions()
    }

    val history = remember { missionManager.getMyStats() }
    val stats = remember(history) { statsEngine.processStats(history) }
    val totalPoints = remember { missionManager.getTotalPoints() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("My Stats", color = MaterialTheme.colorScheme.onBackground) },
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // 1. Overall Progress
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProgressRing(progress = stats.currentMonthProgress)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Total Points: $totalPoints",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Monthly Completion",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }

            // 2. Journal & Goals Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatCard(
                    title = "Journal",
                    value = stats.journalTotal.toString(),
                    subValue = "${stats.journalStreak} day streak",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Goals",
                    value = stats.goalsTotal.toString(),
                    subValue = "${(stats.goalRate * 100).toInt()}% rate",
                    modifier = Modifier.weight(1f)
                )
            }

            // 3. Missions
            StatCard(
                title = "Daily Missions",
                value = stats.missionsTotal.toString(),
                subValue = "${stats.missionStreak} day streak",
                modifier = Modifier.fillMaxWidth()
            )

            // 4. Monthly Insights
            if (stats.insights.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Insights", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    stats.insights.forEach { insight ->
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(insight, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // 5. Achievement Badges
            BadgeGrid(badges = stats.badges)
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatCard(title: String, value: String, subValue: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(title, style = MaterialTheme.typography.labelLarge)
            Text(value, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text(subValue, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }
    }
}
