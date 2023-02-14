package com.chanho.compose_1.ui.screen.bottomnavigation.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chanho.compose_1.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(repo: MovieRepository) : ViewModel() {
    val topRatedMovies = repo.topRatedPagingDataSource().cachedIn(viewModelScope)
}