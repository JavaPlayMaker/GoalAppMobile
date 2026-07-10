package com.example.goalapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class GameType {
    NONE, DEEP_BREATH, POP_BALLOON
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(onBack: () -> Unit) {
    var activeGame by remember { mutableStateOf(GameType.NONE) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        when(activeGame) {
                            GameType.NONE -> "Quick Games"
                            GameType.DEEP_BREATH -> "Deep Breath"
                            GameType.POP_BALLOON -> "Pop the Balloon"
                        }
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (activeGame == GameType.NONE) onBack() else activeGame = GameType.NONE
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (activeGame) {
                GameType.NONE -> GameSelectionMenu(onSelect = { activeGame = it })
                GameType.DEEP_BREATH -> DeepBreathGame()
                GameType.POP_BALLOON -> PopBalloonGame()
            }
        }
    }
}

@Composable
fun GameSelectionMenu(onSelect: (GameType) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pick a 1-minute game",
            style = MaterialTheme.typography.headlineSmall
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSelect(GameType.DEEP_BREATH) }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Deep Breath", style = MaterialTheme.typography.titleMedium)
                Text(text = "Calm your mind with guided breathing.", style = MaterialTheme.typography.bodySmall)
            }
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSelect(GameType.POP_BALLOON) }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Pop the Balloon", style = MaterialTheme.typography.titleMedium)
                Text(text = "Tap the balloons as they appear to pop them!", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun DeepBreathGame() {
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val text = if (scale > 1.5f) "Exhale..." else "Inhale..."

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(150.dp)
                .scale(scale)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f), CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
        
        Spacer(modifier = Modifier.height(100.dp))
        
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

data class Balloon(
    val id: Int,
    val x: Float,
    val y: Float,
    val color: Color
)

@Composable
fun PopBalloonGame() {
    var score by remember { mutableStateOf(0) }
    var balloons by remember { mutableStateOf(emptyList<Balloon>()) }
    var gameActive by remember { mutableStateOf(true) }

    // Spawn balloons
    LaunchedEffect(gameActive) {
        var idCounter = 0
        while (gameActive) {
            if (balloons.size < 8) {
                val newBalloon = Balloon(
                    id = idCounter++,
                    x = Random.nextFloat(),
                    y = Random.nextFloat(),
                    color = listOf(
                        Color(0xFFFF5252), // Red
                        Color(0xFF448AFF), // Blue
                        Color(0xFF69F0AE), // Green
                        Color(0xFFFF4081), // Pink
                        Color(0xFFFFD740)  // Yellow
                    ).random()
                )
                balloons = balloons + newBalloon
            }
            delay(800)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val width = maxWidth
            val height = maxHeight
            
            balloons.forEach { balloon ->
                Box(
                    modifier = Modifier
                        .offset(
                            x = (balloon.x * (width.value - 80)).dp,
                            y = (balloon.y * (height.value - 80)).dp
                        )
                        .size(80.dp)
                        .background(balloon.color, CircleShape)
                        .clickable {
                            score++
                            balloons = balloons.filter { it.id != balloon.id }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Small "shine" on balloon
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .offset(x = (-15).dp, y = (-15).dp)
                            .background(Color.White.copy(alpha = 0.3f), CircleShape)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(onBack = {})
}

@Preview(showBackground = true)
@Composable
fun DeepBreathPreview() {
    DeepBreathGame()
}

@Preview(showBackground = true)
@Composable
fun PopBalloonPreview() {
    PopBalloonGame()
}
