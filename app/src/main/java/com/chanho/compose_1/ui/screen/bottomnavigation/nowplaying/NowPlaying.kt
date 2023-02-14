package com.chanho.compose_1.ui.screen.bottomnavigation.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chanho.compose_1.ui.screen.HomeScreen

@Composable
fun NowPlaying(navController: NavController) {
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(
        navController = navController,
        movies = nowPlayingViewModel.popularMovies,
    )
}