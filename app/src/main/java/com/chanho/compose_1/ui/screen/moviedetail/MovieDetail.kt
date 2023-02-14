package com.chanho.compose_1.ui.screen.moviedetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chanho.compose_1.data.MovieItem
import com.chanho.compose_1.data.datasource.ApiURL
import com.chanho.compose_1.data.model.moviedetail.MovieDetail
import com.chanho.compose_1.navigation.Screen
import com.chanho.compose_1.ui.component.CircularIndeterminateProgressBar
import com.chanho.compose_1.utils.network.DataState
import com.chanho.compose_1.R
import com.chanho.compose_1.data.model.BaseModel
import com.chanho.compose_1.data.model.artist.Artist
import com.chanho.compose_1.data.model.artist.Cast
import com.chanho.compose_1.ui.theme.*


@Composable
fun MovieDetail(navController: NavController, movieId: Int) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val movieDetail = movieDetailViewModel.movieDetail
    val recommendedMovie = movieDetailViewModel.recommendedMovie
    val artist = movieDetailViewModel.artist
    val progressBar = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        movieDetailViewModel.run {
            movieDetailApi(movieId)
            recommendedMovieApi(movieId, 1)
            movieCredit(movieId)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackgroundColor)
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetail>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.data.poster_path)),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(300.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Text(
                            text = it.data.title,
                            modifier = Modifier.padding(top = 10.dp),
                            color = FontColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        ) {
                            Column(Modifier.weight(1f)) {
                                SubTitlePrim(
                                    text = it.data.original_language,
                                )
                                SubTitleSecond(
                                    text = stringResource(R.string.language)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubTitlePrim(
                                    text = it.data.vote_average.toString(),
                                )
                                SubTitleSecond(
                                    text = stringResource(R.string.rating)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubTitlePrim(
                                    text = "${it.data.runtime}h ${it.data.runtime % 60}m"
                                )
                                SubTitleSecond(
                                    text = stringResource(R.string.duration)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubTitlePrim(
                                    text = it.data.release_date
                                )
                                SubTitleSecond(
                                    text = stringResource(R.string.release_date)
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.description),
                            color = FontColor,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = it.data.overview,
                            color = SecondaryFontColor,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        recommendedMovie.value?.let {
                            if (it is DataState.Success<BaseModel>) {
                                RecommendedMovie(it.data.results)
                            }
                        }
                        artist.value?.let {
                            if (it is DataState.Success<Artist>) {
                                ArtistAndCrew(it.data.cast)
                            }
                        }
                    }
                }
            }
        }
        when(recommendedMovie.value){
            is DataState.Success<BaseModel> ->{
                progressBar.value = false
            }
            is DataState.Loading ->{
                progressBar.value = true
            }
            is DataState.Error ->{
                progressBar.value = false
            }
        }
        when(movieDetail.value){
            is DataState.Success<MovieDetail> ->{
                progressBar.value = false
            }
            is DataState.Loading ->{
                progressBar.value = true
            }
            is DataState.Error ->{
                progressBar.value = false
            }
        }
    }
}

@Composable
fun RecommendedMovie(recommendedMovie: List<MovieItem>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.similar),
            color = FontColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(recommendedMovie, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.posterPath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(190.dp)
                            .width(140.dp)
                            .cornerRadius10()
                            .clickable {

                            }
                    )
                }
            })
        }
    }
}

@Composable
fun ArtistAndCrew(cast: List<Cast>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.cast),
            color = FontColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(cast, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 10.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(80.dp)
                            .width(80.dp)
                            .cornerRadius40()
                            .clickable {

                            }
                    )
                    SubTitleSecond(text = item.name)
                }
            })
        }
    }
}

@Composable
fun SubTitlePrim(text: String) {
    Text(text = text, style = MaterialTheme.typography.subTitlePrime)
}

@Composable
fun SubTitleSecond(text: String) {
    Text(text = text, style = MaterialTheme.typography.subTitleSecond)
}
