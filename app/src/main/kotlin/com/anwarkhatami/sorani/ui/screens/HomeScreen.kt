package com.anwarkhatami.sorani.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anwarkhatami.sorani.ui.viewmodel.WordViewModel
import com.anwarkhatami.sorani.ui.navigation.Screens

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: WordViewModel = hiltViewModel()
) {
    val allWords = viewModel.allWords.collectAsState().value
    val totalWordCount = viewModel.totalWordCount.collectAsState().value
    val availableLetters = viewModel.availableLetters.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddWord.route) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "إضافة كلمة")
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("المفضلة") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screens.Favorites.route) },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("المفضلة") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screens.Statistics.route) },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                    label = { Text("الإحصائيات") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screens.Settings.route) },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("الإعدادات") }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    "فەرهەنگی سۆرانی",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    "إجمالي الكلمات: $totalWordCount",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            items(availableLetters) { letter ->
                LetterCard(letter = letter, wordCount = allWords.count { it.kurdishWord.startsWith(letter) })
            }
        }
    }
}

@Composable
fun LetterCard(letter: String, wordCount: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    letter,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "$wordCount كلمات",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
