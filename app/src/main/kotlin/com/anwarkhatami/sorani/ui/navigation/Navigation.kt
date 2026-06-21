package com.anwarkhatami.sorani.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anwarkhatami.sorani.ui.screens.HomeScreen
import com.anwarkhatami.sorani.ui.screens.AddWordScreen
import com.anwarkhatami.sorani.ui.screens.FavoritesScreen
import com.anwarkhatami.sorani.ui.screens.StatisticsScreen
import com.anwarkhatami.sorani.ui.screens.SettingsScreen

seal class Screens(val route: String) {
    object Home : Screens("home")
    object AddWord : Screens("add_word")
    object Favorites : Screens("favorites")
    object Statistics : Screens("statistics")
    object Settings : Screens("settings")
    object WordDetails : Screens("word_details/{wordId}") {
        fun createRoute(wordId: Int) = "word_details/$wordId"
    }
}

@Composable
fun SoraniNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.AddWord.route) {
            AddWordScreen(navController)
        }
        composable(Screens.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(Screens.Statistics.route) {
            StatisticsScreen(navController)
        }
        composable(Screens.Settings.route) {
            SettingsScreen(navController)
        }
    }
}
