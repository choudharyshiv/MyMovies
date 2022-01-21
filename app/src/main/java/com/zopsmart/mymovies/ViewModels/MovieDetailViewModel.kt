package com.zopsmart.mymovies.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zopsmart.mymovies.model.MovieDetailModel
import com.zopsmart.mymovies.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel  (val repository: MovieDetailRepository) : ViewModel(){

fun getMovieDetail(movieId:Int)
{
    viewModelScope.launch(Dispatchers.IO)
    {
        repository.getMovieDetail(movieId)

    }
}

        val MoviesDetailLiveData : LiveData<MovieDetailModel>
            get() = repository.MovieDetailLiveData



    }