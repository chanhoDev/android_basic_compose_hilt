package com.chanho.compose_1.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.chanho.compose_1.data.datasource.ApiService
import com.chanho.compose_1.data.datasource.paging.PopularPagingDataSource
import com.chanho.compose_1.data.datasource.paging.TopRatedPagingDataSource
import com.chanho.compose_1.data.model.BaseModel
import com.chanho.compose_1.data.model.Genres
import com.chanho.compose_1.data.model.artist.Artist
import com.chanho.compose_1.data.model.moviedetail.MovieDetail
import com.chanho.compose_1.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.recommendedMovie(movieId, page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


    suspend fun search(searchKey: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.search(searchKey)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
//
//    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
//        emit(DataState.Loading)
//        try {
//            val artistDetailResult = apiService.artistDetail(personId)
//            emit(DataState.Success(artistDetailResult))
//
//        } catch (e: Exception) {
//            emit(DataState.Error(e))
//        }
//    }
//
//    fun nowPlayingPagingDataSource() = Pager(
//        pagingSourceFactory = { NowPlayingPagingDataSource(apiService) },
//        config = PagingConfig(pageSize = 1)
//    ).flow
//
    fun popularPagingDataSource() = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun topRatedPagingDataSource() = Pager(
        pagingSourceFactory =  { TopRatedPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow
//
//    fun topRatedPagingDataSource() = Pager(
//        pagingSourceFactory = { TopRatedPagingDataSource(apiService) },
//        config = PagingConfig(pageSize = 1)
//    ).flow
//
//    fun upcomingPagingDataSource() = Pager(
//        pagingSourceFactory = { UpcomingPagingDataSource(apiService) },
//        config = PagingConfig(pageSize = 1)
//    ).flow
//
//    fun genrePagingDataSource(genreId: String) = Pager(
//        pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
//        config = PagingConfig(pageSize = 1)
//    ).flow

}