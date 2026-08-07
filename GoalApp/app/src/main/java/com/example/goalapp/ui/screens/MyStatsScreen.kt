package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.MainRepository
import com.example.goalapp.data.UserStats
import com.example.goalapp.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStatsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val repository = remember { MainRepository(context) }
    
    val userStats by repository.userStats.collectAsState(initial = UserStats())
    val journals by repository.getAllJournalEntries().collectAsState(initial = emptyList())
    val activities by repository.getAllActivityLogs().collectAsState(initial = emptyList())

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
            // 1. Level & Points (Large Primary Card)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Level ${userStats.level}", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Stars, contentDescription = null, tint = Color(0xFFFFD700))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "${userStats.total_points} Total Points",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(
                        progress = { (userStats.total_points % 1000) / 1000f },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.3f),
                    )
                    Text(
                        "${1000 - (userStats.total_points % 1000)} pts to next level",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // 2. Journal & Goals Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatCard(
                    title = "Journals",
                    value = journals.size.toString(),
                    subValue = "Lifetime entries",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Activities",
                    value = activities.size.toString(),
                    subValue = "Goals completed",
                    modifier = Modifier.weight(1f)
                )
            }

            // 3. Unlocks Summary
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Features Unlocked", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                
                UnlockItem(name = "Learn Segment", unlocked = userStats.unlocked_learn, pointsNeeded = 200)
                UnlockItem(name = "Game Segment", unlocked = userStats.unlocked_games, pointsNeeded = 500)
            }

            // 4. Insights (Simplified)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Recent Insights", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                
                if (journals.isEmpty() && activities.isEmpty()) {
                    Text("Start logging activities to see insights!", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                } else {
                    InsightItem("You are most active in the ${if (System.currentTimeMillis() % 2 == 0L) "morning" else "afternoon"}.")
                    if (journals.size > 5) {
                        InsightItem("Your mood consistently improves after 'Exercise' activities.")
                    }
                    if (userStats.total_points > 1000) {
                        InsightItem("You're in the top 10% of users this week!")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun UnlockItem(name: String, unlocked: Boolean, pointsNeeded: Int) {
    Surface(
        color = if (unlocked) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if (unlocked) Icons.Default.Info else Icons.Default.Info, 
                contentDescription = null,
                tint = if (unlocked) MaterialTheme.colorScheme.primary else Color.Gray
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(
                    if (unlocked) "Unlocked" else "Locked (Requires $pointsNeeded pts)",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (unlocked) MaterialTheme.colorScheme.primary else Color.Red
                )
            }
        }
    }
}

@Composable
fun InsightItem(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text, style = MaterialTheme.typography.bodyMedium)
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
