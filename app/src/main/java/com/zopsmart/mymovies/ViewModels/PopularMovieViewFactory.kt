package com.zopsmart.mymovies.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zopsmart.mymovies.repository.PopularMovieRepository

class PopularMovieViewFactory(private val repository : PopularMovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(repository) as T
    }


}