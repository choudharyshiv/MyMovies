package com.zopsmart.mymovies.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zopsmart.mymovies.repository.MovieDetailRepository

class MovieDetailViewFactory(private val repository: MovieDetailRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T


    }
}
