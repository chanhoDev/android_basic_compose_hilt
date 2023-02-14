package com.chanho.compose_1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chanho.compose_1.R
import com.chanho.compose_1.ui.screen.bottomnavigation.nowplaying.NowPlaying
import com.chanho.compose_1.ui.screen.bottomnavigation.toprated.TopRated
import com.chanho.compose_1.ui.screen.moviedetail.MovieDetail

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier) {

        composable(Screen.Home.route) {
            NowPlaying(navController = navController)
        }

        composable(Screen.TopRated.route) {
            TopRated(navController = navController)
        }

        composable(
            Screen.MovieDetail.route.plus(Screen.MovieDetail.objectPath),
            arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
            if (movieId != null) {
                MovieDetail(navController = navController, movieId = movieId)
            }
        }
    }
}