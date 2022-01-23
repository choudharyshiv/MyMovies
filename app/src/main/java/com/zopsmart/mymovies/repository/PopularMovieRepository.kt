package com.zopsmart.mymovies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zopsmart.mymovies.api.ApiInterface
import com.zopsmart.mymovies.model.PopularMovieModel

class PopularMovieRepository(private val ApiInterface: ApiInterface) {


    private val mutablePopularMovieLiveData = MutableLiveData<PopularMovieModel>()

    val popularMovieLiveData: LiveData<PopularMovieModel>
        get() = mutablePopularMovieLiveData


    suspend fun getPopularMovie() {
        val result = ApiInterface.getPopularMovies()
        if (result.body() != null) {
            Log.d("sss", "Response of Popular Movie : " + result.body().toString())
            mutablePopularMovieLiveData.postValue(result.body())
        }
    }



}