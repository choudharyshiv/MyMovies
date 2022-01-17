package com.zopsmart.mymovies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.RetrofitHelper.API_KEY
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.model.PopularMovieModel

class PopularMovieRepository(private val apiInterface: apiInterface) {


    private  val  mutablePopularMovieLiveData = MutableLiveData<PopularMovieModel>()

    val popularMovieLiveData : LiveData<PopularMovieModel>
    get() = mutablePopularMovieLiveData

//change to development
    suspend fun getPopularMovie()
    {
        val result = apiInterface.getPopularMovies(RetrofitHelper.API_KEY)
        if(result.body() != null)
        {
            Log.d("kkk","Response of Popular Movie : "+result.body().toString())
            mutablePopularMovieLiveData.postValue(result.body())
        }
    }
}