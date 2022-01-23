package com.zopsmart.mymovies.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zopsmart.mymovies.model.PopularMovieModel
import kotlinx.coroutines.launch
import com.zopsmart.mymovies.repository.PopularMovieRepository
import kotlinx.coroutines.Dispatchers

class PopularMovieViewModel (private val repository: PopularMovieRepository) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.getPopularMovie()
        }
    }
    val popularMoviesLiveData : LiveData<PopularMovieModel>
    get() = repository.popularMovieLiveData



}