package com.anwarkhatami.sorani.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anwarkhatami.sorani.ui.viewmodel.WordViewModel

@Composable
fun StatisticsScreen(
    navController: NavHostController,
    viewModel: WordViewModel = hiltViewModel()
) {
    val totalWords = viewModel.totalWordCount.collectAsState().value
    val recentWords = viewModel.recentWords.collectAsState().value
    val availableLetters = viewModel.availableLetters.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("الإحصائيات") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "رجوع")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard("إجمالي الكلمات", totalWords.toString())
            StatCard("عدد الحروف", availableLetters.size.toString())
            StatCard("الكلمات الحديثة", recentWords.size.toString())
            
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                value,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
