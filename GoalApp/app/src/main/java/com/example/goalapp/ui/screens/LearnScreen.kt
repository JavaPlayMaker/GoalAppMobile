package com.example.goalapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.goalapp.R
import com.example.goalapp.data.MissionManager

enum class LearnPage {
    MAIN, HOW_TO_DEAL, ART_OF_ALONE, ADVANCED_TIPS, TOPIC_DETAIL
}

data class Topic(
    val title: String,
    val description: String,
    val content: String,
)

val howToDealTopics = listOf(
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

val artOfAloneTopics = listOf(
    Topic(
        "Observe",
        "Ground yourself in the present.",
        "Find a quiet spot and simply notice. What sounds do you hear? What colors catch your eye? Observing without judging grounds you in the present and quietens the internal noise."
    ),
    Topic(
        "Document",
        "Understand your inner dialogue.",
        "Keep a small notebook or use your journal. Write down one thought that usually passes you by. Capturing these fleeting moments helps you identify patterns in your thinking and emotions."
    ),
    Topic(
        "Unplug",
        "Find the stillness underneath.",
        "Spend 10 minutes without any screens. No phone, no TV, no music. The initial restlessness is normal; sit with it until you find the stillness that exists when you aren't being constantly stimulated."
    )
)

val advancedTipsTopics = listOf(
    Topic(
        "The 5-Minute Rule",
        "Overcome procrastination instantly.",
        "Whenever you feel overwhelmed by a task, commit to it for just 5 minutes. Often, the hardest part is starting. Once you break the seal, the flow state is much easier to reach."
    ),
    Topic(
        "Digital Detox Rituals",
        "Create intentional boundaries.",
        "Instead of just 'unplugging', create a ritual. Light a candle, prepare a specific tea, or sit in a specific chair that is a 'no-phone zone'. Your brain will begin to associate these triggers with deep relaxation."
    ),
    Topic(
        "Mindful Reflection",
        "Master your emotional responses.",
        "At the end of each day, don't just record what happened. Record how you reacted. Identifying patterns in your emotional response is the first step toward masterfully navigating your own mind."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(onBack: () -> Unit, onDone: () -> Unit = {}) {
    val context = LocalContext.current
    val missionManager = remember { MissionManager(context) }
    var currentPage by remember { mutableStateOf(LearnPage.MAIN) }
    var previousPage by remember { mutableStateOf(LearnPage.MAIN) }
    var selectedTopic by remember { mutableStateOf<Topic?>(null) }
    var isUnlocked by remember { mutableStateOf(missionManager.isLearnPageUnlocked()) }
    var showUnlockError by remember { mutableStateOf(false) }

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
                            LearnPage.ART_OF_ALONE -> "The Art of Being Alone"
                            LearnPage.ADVANCED_TIPS -> "Advanced Daily Tips"
                        },
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = {
                        when (currentPage) {
                            LearnPage.MAIN -> onBack()
                            LearnPage.TOPIC_DETAIL -> currentPage = previousPage
                            else -> currentPage = LearnPage.MAIN
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
                LearnPage.MAIN -> MainLearnMenu { currentPage = it }
                LearnPage.HOW_TO_DEAL -> TopicListMenu("Strategies for Solitude", howToDealTopics) {
                    selectedTopic = it
                    previousPage = LearnPage.HOW_TO_DEAL
                    currentPage = LearnPage.TOPIC_DETAIL
                }
                LearnPage.ART_OF_ALONE -> TopicListMenu("Mastering Solitude", artOfAloneTopics) {
                    selectedTopic = it
                    previousPage = LearnPage.ART_OF_ALONE
                    currentPage = LearnPage.TOPIC_DETAIL
                }
                LearnPage.ADVANCED_TIPS -> {
                    if (isUnlocked) {
                        TopicListMenu("Advanced Daily Tips", advancedTipsTopics) {
                            selectedTopic = it
                            previousPage = LearnPage.ADVANCED_TIPS
                            currentPage = LearnPage.TOPIC_DETAIL
                        }
                    } else {
                        LockedContentView(
                            pointsRequired = 100,
                            userPoints = missionManager.getTotalPoints(),
                            onUnlock = {
                                if (missionManager.spendPoints(100)) {
                                    missionManager.unlockLearnPage()
                                    isUnlocked = true
                                } else {
                                    showUnlockError = true
                                }
                            }
                        )
                    }
                }
                LearnPage.TOPIC_DETAIL -> TopicDetailView(selectedTopic) { 
                    onDone()
                    currentPage = previousPage 
                }
            }

            if (showUnlockError) {
                AlertDialog(
                    onDismissRequest = { showUnlockError = false },
                    title = { Text("Not Enough Points") },
                    text = { Text("You need at least 100 points to unlock this content. Complete more daily missions to earn points!") },
                    confirmButton = {
                        TextButton(onClick = { showUnlockError = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainLearnMenu(onNavigate: (LearnPage) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
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
            onClick = { onNavigate(LearnPage.ART_OF_ALONE) }
        )

        LearnCard(
            title = "How to Deal With It",
            description = "Practical strategies for managing difficult moments.",
            imageRes = R.drawable.phpink,
            onClick = { onNavigate(LearnPage.HOW_TO_DEAL) }
        )

        LearnCard(
            title = "Advanced Daily Tips",
            description = "Master your routine and mindset (100 Points).",
            imageRes = R.drawable.phgreen,
            onClick = { onNavigate(LearnPage.ADVANCED_TIPS) }
        )
    }
}

@Composable
fun LockedContentView(pointsRequired: Int, userPoints: Int, onUnlock: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.White.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Content Locked",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Text(
            text = "Unlock this advanced guide to master your journey.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onUnlock,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Unlock for $pointsRequired Points")
        }
        Text(
            text = "Current Balance: $userPoints Points",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 12.dp)
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
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
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
fun TopicListMenu(title: String, topicList: List<Topic>, onSelectTopic: (Topic) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        topicList.forEach { topic ->
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
