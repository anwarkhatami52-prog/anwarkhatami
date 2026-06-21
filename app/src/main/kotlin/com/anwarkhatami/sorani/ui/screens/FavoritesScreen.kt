package com.anwarkhatami.sorani.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anwarkhatami.sorani.ui.viewmodel.WordViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: WordViewModel = hiltViewModel()
) {
    val favorites = viewModel.favoriteWords.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("المفضلة") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "رجوع")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (favorites.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    "لا توجد كلمات مفضلة",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites) { word ->
                    WordCard(word = word, onFavoriteToggle = { viewModel.toggleFavorite(word.id, word.isFavorite) })
                }
            }
        }
    }
}

@Composable
fun WordCard(word: com.anwarkhatami.sorani.data.local.entity.WordEntity, onFavoriteToggle: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    word.kurdishWord,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                IconButton(onClick = onFavoriteToggle) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "المفضلة",
                        tint = if (word.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline
                    )
                }
            }
            if (word.pronunciation.isNotEmpty()) {
                Text(
                    "النطق: ${word.pronunciation}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                "المعنى: ${word.meaning}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (word.explanation.isNotEmpty()) {
                Text(
                    "الشرح: ${word.explanation}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
