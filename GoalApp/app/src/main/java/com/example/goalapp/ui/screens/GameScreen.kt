package com.example.goalapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.R
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class GameType {
    NONE, DEEP_BREATH, POP_BALLOON
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(onBack: () -> Unit, onDone: () -> Unit = {}) {
    var activeGame by remember { mutableStateOf(GameType.NONE) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        when(activeGame) {
                            GameType.NONE -> "Quick Games"
                            GameType.DEEP_BREATH -> "Deep Breath"
                            GameType.POP_BALLOON -> "Pop the Balloon"
                        },
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (activeGame == GameType.NONE) onBack() else activeGame = GameType.NONE
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
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
                GameType.DEEP_BREATH -> DeepBreathGame(onDone = { 
                    onDone()
                    activeGame = GameType.NONE 
                })
                GameType.POP_BALLOON -> PopBalloonGame(onDone = { 
                    onDone()
                    activeGame = GameType.NONE 
                })
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
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        GameCard(
            title = "Deep Breath",
            description = "Calm your mind with guided breathing.",
            imageRes = R.drawable.phgreen,
            onClick = { onSelect(GameType.DEEP_BREATH) }
        )

        GameCard(
            title = "Pop the Balloon",
            description = "Tap the balloons as they appear to pop them!",
            imageRes = R.drawable.phpink,
            onClick = { onSelect(GameType.POP_BALLOON) }
        )
    }
}

@Composable
fun GameCard(title: String, description: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        Column {
            Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.height(100.dp),
                contentScale = ContentScale.Fit
            )
        }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(text = description, style = MaterialTheme.typography.bodySmall, color = Color.White)
            }
        }
    }
}

@Composable
fun DeepBreathGame(onDone: () -> Unit) {
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
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(150.dp)
                .scale(scale)
                .background(Color.White.copy(alpha = 0.3f), CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
        
        Spacer(modifier = Modifier.height(60.dp))
        
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Done")
        }
    }
}

data class Balloon(
    val id: Int,
    val x: Float,
    val y: Float
)

@Composable
fun PopBalloonGame(onDone: () -> Unit) {
    var score by remember { mutableStateOf(0) }
    var balloons by remember { mutableStateOf(emptyList<Balloon>()) }
    var gameActive by remember { mutableStateOf(true) }

    LaunchedEffect(gameActive) {
        var idCounter = 0
        while (gameActive) {
            if (balloons.size < 8) {
                val newBalloon = Balloon(
                    id = idCounter++,
                    x = Random.nextFloat(),
                    y = Random.nextFloat()
                )
                balloons = balloons + newBalloon
            }
            delay(800)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(bottom = 24.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        
        BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
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
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                        .clickable {
                            score++
                            balloons = balloons.filter { it.id != balloon.id }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .offset(x = (-15).dp, y = (-15).dp)
                            .background(Color.White.copy(alpha = 0.3f), CircleShape)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                gameActive = false
                onDone()
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(56.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Done")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    com.example.goalapp.ui.theme.GoalAppTheme {
        GameScreen(onBack = {})
    }
}
