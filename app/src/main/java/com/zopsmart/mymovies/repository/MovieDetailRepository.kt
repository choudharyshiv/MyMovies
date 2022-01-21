package com.zopsmart.mymovies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.model.MovieDetailModel

class MovieDetailRepository (private val apiInterface: apiInterface){


    private val mutableSpecificMovieLiveData =  MutableLiveData<MovieDetailModel>()

    val specificMovieLiveData : LiveData<MovieDetailModel>
        get() = mutableSpecificMovieLiveData


    suspend fun getSpecificMovieDetails(movieId : Int)
    {
        val result = apiInterface.getMovieDetails(movieId)
        Log.d("kkk","result of repo : "+result.toString()+"result of body :"+result.body())
        if(result?.body() != null)
        {
            Log.d("kkk","specific movie response : "+result.body())
            mutableSpecificMovieLiveData.postValue(result.body())
        }
    }

}