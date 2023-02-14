package com.chanho.compose_1.ui.screen.bottomnavigation.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chanho.compose_1.navigation.Screen
import com.chanho.compose_1.ui.screen.HomeScreen

@Composable
fun TopRated(navController: NavController) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    HomeScreen(
        navController = navController,
        movies = topRatedViewModel.topRatedMovies,
    )
}