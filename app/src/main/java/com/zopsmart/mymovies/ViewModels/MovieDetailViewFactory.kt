package com.zopsmart.mymovies.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zopsmart.mymovies.repository.MovieDetailRepository

class MovieDetailViewFactory (private val repository : MovieDetailRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T


    }
}
