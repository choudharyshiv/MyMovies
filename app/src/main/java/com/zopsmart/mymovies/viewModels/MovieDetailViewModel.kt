package com.zopsmart.mymovies.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zopsmart.mymovies.model.MovieDetailModel
import com.zopsmart.mymovies.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel  (private val repository: MovieDetailRepository) : ViewModel(){

fun getMovieDetail(movieId:Int)
{
    viewModelScope.launch(Dispatchers.IO)
    {
        Log.d("kkk","movie detail view model called")
        repository.getSpecificMovieDetails(movieId)

    }
}

        val movieDetailLiveData : LiveData<MovieDetailModel>
            get() = repository.specificMovieLiveData



    }