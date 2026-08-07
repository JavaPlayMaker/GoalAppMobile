package com.example.goalapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms of Service") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Terms of Service for Goal",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Last updated: October 2023",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TermsSection("Welcome", "Welcome to Goal! These Terms of Service (the “Terms”) govern your access to and use of Goal and its related services, websites, and any associated software, features, content, and tools (collectively, the “Services”). By accessing or using the Services, you agree to be bound by these Terms. If you do not agree, do not use the Services.\n\nIf you are under 18 years old, you may not use the Services without parental or guardian consent. Please read these Terms carefully before using the Services.")
            
            TermsSection("1. Acceptance of Terms", "By accessing or using the Services, you agree to these Terms and any future amendments. Continued use constitutes acceptance. We may notify you of material changes via email or in-app notification.")
            
            TermsSection("2. Eligibility", "• You must be at least 18 years old (or have parental consent).\n• You must provide accurate information.\n• You are responsible for account confidentiality.")
            
            TermsSection("3. Account Security", "You are solely responsible for all activity under your account. Notify us immediately of any compromise. We reserve the right to suspend accounts for violations.")
            
            TermsSection("4. Prohibited Uses", "You agree NOT to: violate laws, post harmful content, interfere with services, reverse engineer software, use scrapers/bots, or impersonate others.")
            
            TermsSection("5. User Content", "You retain ownership of your content but grant us a license to use it to provide the Services. You are responsible for content legality. We may remove inappropriate content.")
            
            TermsSection("6. Intellectual Property", "All app functionality and content are our exclusive property. You may not copy or distribute any part without permission.")
            
            TermsSection("7. Privacy", "Your privacy is important. Please review our Privacy Policy to understand our data practices.")
            
            TermsSection("8. Payments", "Premium features require payment. Payments are non-refundable unless required by law.")
            
            TermsSection("9. Disclaimers", "SERVICES ARE PROVIDED “AS IS”. We do not guarantee error-free operation and are not responsible for losses arising from use.")
            
            TermsSection("10. Limitation of Liability", "To the fullest extent permitted by law, we are not liable for indirect or consequential damages. Our total liability is limited to the amount paid in the last 12 months.")
            
            TermsSection("11. Indemnification", "You agree to indemnify us against claims arising from your use or violation of these Terms.")
            
            TermsSection("12. Termination", "We may terminate access at any time. You may delete your account at any time.")
            
            TermsSection("13. Governing Law", "These Terms are governed by the laws of your jurisdiction. Disputes will be resolved in local courts.")
            
            TermsSection("14. Miscellaneous", "These Terms constitute the entire agreement. If any provision is invalid, others remain. We may assign these Terms to affiliates.")
            
            TermsSection("15. Contact Us", "Coming Soon")
        }
    }
}

@Composable
fun TermsSection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
