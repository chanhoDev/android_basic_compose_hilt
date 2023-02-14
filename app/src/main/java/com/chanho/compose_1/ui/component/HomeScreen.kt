package com.chanho.compose_1.ui.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.chanho.compose_1.data.MovieItem
import com.chanho.compose_1.data.datasource.ApiURL
import com.chanho.compose_1.navigation.Screen
import com.chanho.compose_1.ui.component.CircularIndeterminateProgressBar
import com.chanho.compose_1.ui.screen.mainscreen.currentRout
import com.chanho.compose_1.ui.theme.DefaultBackgroundColor
import com.chanho.compose_1.ui.theme.cornerRadius10
import com.chanho.compose_1.utils.items
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    movies: Flow<PagingData<MovieItem>>
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val moviesItems: LazyPagingItems<MovieItem> = movies.collectAsLazyPagingItems()

    BackHandler(enabled = (currentRout(navController = navController) == Screen.Home.route)) {
        openDialog.value = true
    }
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
            content = {
                items(moviesItems) { item ->
                    item?.let {
                        MovieItemView(item = item, navController = navController)
                    }
                }
            })
    }
}

@Composable
fun MovieItemView(item: MovieItem, navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = ApiURL.IMAGE_URL.plus(item.posterPath)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =Modifier
                .size(250.dp)
                .cornerRadius10()
                .clickable {
                    navController.navigate(Screen.MovieDetail.route.plus("/${item.id}"))
                }
        )
    }
}