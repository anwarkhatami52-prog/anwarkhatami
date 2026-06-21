package com.anwarkhatami.sorani.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(
    navController: NavHostController
) {
    var isDarkMode by remember { mutableStateOf(false) }
    var fontSize by remember { mutableStateOf(14) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("الإعدادات") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "الإعدادات العامة",
                style = MaterialTheme.typography.titleLarge
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text("الوضع الليلي")
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it }
                )
            }
            
            Text(
                "حجم الخط: $fontSize",
                style = MaterialTheme.typography.bodyMedium
            )
            Slider(
                value = fontSize.toFloat(),
                onValueChange = { fontSize = it.toInt() },
                valueRange = 12f..20f,
                modifier = Modifier.fillMaxWidth()
            )
            
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            
            Text(
                "النسخة الاحتياطية",
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تصدير القاموس")
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("استيراد قاموس")
            }
        }
    }
}
