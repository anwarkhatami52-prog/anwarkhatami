package com.anwarkhatami.sorani.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anwarkhatami.sorani.data.local.entity.WordEntity
import com.anwarkhatami.sorani.ui.viewmodel.WordViewModel

@Composable
fun AddWordScreen(
    navController: NavHostController,
    viewModel: WordViewModel = hiltViewModel()
) {
    var kurdishWord by remember { mutableStateOf("") }
    var pronunciation by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }
    var explanation by remember { mutableStateOf("") }
    var examples by remember { mutableStateOf("") }
    var synonyms by remember { mutableStateOf("") }
    var antonyms by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var source by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("عامة") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("إضافة كلمة جديدة") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = kurdishWord,
                onValueChange = { kurdishWord = it },
                label = { Text("الكلمة الكردية") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = pronunciation,
                onValueChange = { pronunciation = it },
                label = { Text("النطق") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = meaning,
                onValueChange = { meaning = it },
                label = { Text("المعنى") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = explanation,
                onValueChange = { explanation = it },
                label = { Text("الشرح") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
            OutlinedTextField(
                value = examples,
                onValueChange = { examples = it },
                label = { Text("أمثلة") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
            OutlinedTextField(
                value = synonyms,
                onValueChange = { synonyms = it },
                label = { Text("المرادفات") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = antonyms,
                onValueChange = { antonyms = it },
                label = { Text("الأضداد") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("ملاحظات") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
            OutlinedTextField(
                value = source,
                onValueChange = { source = it },
                label = { Text("المصدر") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (kurdishWord.isNotEmpty() && meaning.isNotEmpty()) {
                        val word = WordEntity(
                            kurdishWord = kurdishWord,
                            pronunciation = pronunciation,
                            meaning = meaning,
                            explanation = explanation,
                            examples = examples,
                            synonyms = synonyms,
                            antonyms = antonyms,
                            notes = notes,
                            source = source,
                            category = category
                        )
                        viewModel.addWord(word)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("حفظ الكلمة", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
