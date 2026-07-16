package com.example.goalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.R

enum class LearnPage {
    MAIN, HOW_TO_DEAL, TOPIC_DETAIL
}

data class Topic(
    val title: String,
    val description: String,
    val content: String
)

val topics = listOf(
    Topic(
        "Clean your room",
        "A tidy space leads to a tidy mind.",
        "Your environment directly affects your mental state. Start small: make your bed, organize your desk, or clear one shelf. Physical order creates a sense of control and accomplishment."
    ),
    Topic(
        "Exercise",
        "Move your body to clear your head.",
        "Exercise releases endorphins that naturally improve your mood. Whether it's a 10-minute walk or a full workout, movement helps process heavy emotions and reduces stress."
    ),
    Topic(
        "Hobbies",
        "Rediscover what you love to do.",
        "Hobbies provide a flow state where time disappears. Engaging in creative or technical activities gives you a sense of purpose and helps you build skills that are uniquely yours."
    ),
    Topic(
        "Relationship",
        "A quick chat can change your whole day.",
        "Solitude doesn't mean isolation. Reach out to a friend or family member for a quick chat. Sharing a laugh or just hearing a familiar voice helps you stay connected and reminds you that you're not alone."
    ),
    Topic(
        "Food",
        "Nourish yourself from the inside out.",
        "What you eat impacts how you feel. Taking the time to cook a healthy meal is an act of self-care. Focus on whole foods that give you sustained energy and mental clarity."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(onBack: () -> Unit) {
    var currentPage by remember { mutableStateOf(LearnPage.MAIN) }
    var selectedTopic by remember { mutableStateOf<Topic?>(null) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        when (currentPage) {
                            LearnPage.MAIN -> "Learn"
                            LearnPage.HOW_TO_DEAL -> "How to Deal"
                            LearnPage.TOPIC_DETAIL -> selectedTopic?.title ?: "Details"
                        },
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = {
                        when (currentPage) {
                            LearnPage.MAIN -> onBack()
                            LearnPage.HOW_TO_DEAL -> currentPage = LearnPage.MAIN
                            LearnPage.TOPIC_DETAIL -> currentPage = LearnPage.HOW_TO_DEAL
                        }
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
            when (currentPage) {
                LearnPage.MAIN -> MainLearnMenu(onNavigate = { currentPage = it })
                LearnPage.HOW_TO_DEAL -> HowToDealMenu(onSelectTopic = {
                    selectedTopic = it
                    currentPage = LearnPage.TOPIC_DETAIL
                })
                LearnPage.TOPIC_DETAIL -> TopicDetailView(selectedTopic, onDone = { currentPage = LearnPage.HOW_TO_DEAL })
            }
        }
    }
}

@Composable
fun MainLearnMenu(onNavigate: (LearnPage) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What would you like to explore?",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        LearnCard(
            title = "The Art of Being Alone",
            description = "Solitude is a strength. It's a time to recharge and discover yourself.",
            imageRes = R.drawable.phblue,
            onClick = {}
        )

        LearnCard(
            title = "How to Deal With It",
            description = "Practical strategies for managing difficult moments.",
            imageRes = R.drawable.phpink,
            onClick = { onNavigate(LearnPage.HOW_TO_DEAL) }
        )
    }
}

@Composable
fun LearnCard(title: String, description: String, imageRes: Int, onClick: () -> Unit) {
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
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(120.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(text = description, style = MaterialTheme.typography.bodySmall, color = Color.White)
            }
        }
    }
}

@Composable
fun HowToDealMenu(onSelectTopic: (Topic) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Strategies for Solitude",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        topics.forEach { topic ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSelectTopic(topic) },
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = topic.title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                    Text(text = topic.description, style = MaterialTheme.typography.bodySmall, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TopicDetailView(topic: Topic?, onDone: () -> Unit) {
    topic?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = it.title, style = MaterialTheme.typography.headlineMedium, color = Color.White)
            HorizontalDivider(color = Color.White.copy(alpha = 0.5f))
            Text(
                text = it.content,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 28.sp,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Small steps make a big difference.",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

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
}

@Preview(showBackground = true)
@Composable
fun LearnScreenPreview() {
    LearnScreen(onBack = {})
}
