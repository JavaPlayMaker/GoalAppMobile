package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.goalapp.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(onComplete: (UserProfile) -> Unit) {
    var step by remember { mutableStateOf(1) }
    val totalSteps = 8
    
    // Form State
    var livingSituation by remember { mutableStateOf(LivingSituation.OTHER) }
    var socialEnjoyment by remember { mutableStateOf("Sometimes") }
    var exerciseFrequency by remember { mutableStateOf(ExerciseFrequency.RARELY) }
    var employmentStatus by remember { mutableStateOf(EmploymentStatus.OTHER) }
    var budgetPreference by remember { mutableStateOf(BudgetPreference.NO_PREFERENCE) }
    var selectedInterests by remember { mutableStateOf(setOf<Interest>()) }
    var selectedObstacles by remember { mutableStateOf(setOf<Obstacle>()) }
    var focus by remember { mutableStateOf(GoalFocus.FIND_DO) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                LinearProgressIndicator(
                    progress = { step / totalSteps.toFloat() },
                    modifier = Modifier.fillMaxWidth().height(4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                )
                CenterAlignedTopAppBar(
                    title = { Text("About You", color = MaterialTheme.colorScheme.onBackground) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
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
            when (step) {
                1 -> {
                    Text("Let's get to know you", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("Who do you live with?", LivingSituation.entries, livingSituation) { livingSituation = it as LivingSituation }
                }
                2 -> {
                    Text("Let's get to know you", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("Do you enjoy being around people?", listOf("Yes", "Sometimes", "No"), socialEnjoyment) { socialEnjoyment = it as String }
                }
                3 -> {
                    Text("Let's get to know you", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("How often do you exercise?", ExerciseFrequency.entries, exerciseFrequency) { exerciseFrequency = it as ExerciseFrequency }
                }
                4 -> {
                    Text("Life & Preferences", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("Current situation?", EmploymentStatus.entries, employmentStatus) { employmentStatus = it as EmploymentStatus }
                }
                5 -> {
                    Text("Life & Preferences", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("Typical budget for activities?", BudgetPreference.entries, budgetPreference) { budgetPreference = it as BudgetPreference }
                }
                6 -> {
                    Text("Life & Preferences", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileMultiSelectSection("What usually stops you?", Obstacle.entries, selectedObstacles) { obstacle ->
                        val item = obstacle as Obstacle
                        selectedObstacles = if (selectedObstacles.contains(item)) selectedObstacles - item else selectedObstacles + item
                    }
                }
                7 -> {
                    Text("Interests & Goals", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileMultiSelectSection("What do you enjoy?", Interest.entries, selectedInterests) { interest ->
                        val item = interest as Interest
                        selectedInterests = if (selectedInterests.contains(item)) selectedInterests - item else selectedInterests + item
                    }
                }
                8 -> {
                    Text("Interests & Goals", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    ProfileOptionSection("Goal should help me...", GoalFocus.entries, focus) { focus = it as GoalFocus }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (step > 1) {
                    Button(
                        onClick = { step-- },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF2D2D2D)
                        )
                    ) {
                        Text("Back")
                    }
                }

                Button(
                    onClick = {
                        if (step < totalSteps) step++ else {
                            onComplete(UserProfile(
                                livingSituation, socialEnjoyment, exerciseFrequency, 
                                employmentStatus, budgetPreference, selectedInterests.toList(), 
                                selectedObstacles.toList(), focus
                            ))
                        }
                    },
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(if (step < totalSteps) "Next" else "Finish")
                }
            }
        }
    }
}

@Composable
fun ProfileOptionSection(title: String, options: List<Any>, selected: Any, onSelect: (Any) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
        options.forEach { option ->
            val label = when (option) {
                is LivingSituation -> option.displayName
                is ExerciseFrequency -> option.displayName
                is EmploymentStatus -> option.displayName
                is BudgetPreference -> option.displayName
                is GoalFocus -> option.displayName
                else -> option.toString()
            }
            
            val isSelected = option == selected
            
            Button(
                onClick = { onSelect(option) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                    contentColor = if (isSelected) Color.White else Color(0xFF2D2D2D)
                )
            ) {
                Text(label)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileMultiSelectSection(title: String, options: List<Any>, selected: Set<Any>, onToggle: (Any) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                val label = when (option) {
                    is Interest -> option.displayName
                    is Obstacle -> option.displayName
                    else -> option.toString()
                }
                
                val isSelected = selected.contains(option)
                
                Button(
                    onClick = { onToggle(option) },
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                        contentColor = if (isSelected) Color.White else Color(0xFF2D2D2D)
                    ),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(label)
                }
            }
        }
    }
}
