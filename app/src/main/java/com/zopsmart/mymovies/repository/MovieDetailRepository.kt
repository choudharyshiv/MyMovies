package com.zopsmart.mymovies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.model.MovieDetailModel

class MovieDetailRepository (private val apiInterface: apiInterface){

    private  val  mutableMovieDetailLiveData = MutableLiveData<MovieDetailModel>()

    val MovieDetailLiveData : LiveData<MovieDetailModel>
        get() = mutableMovieDetailLiveData


    suspend fun getMovieDetail(MovieId: Int)
    {

        val result = apiInterface.getMovieDetails(MovieId,RetrofitHelper.API_KEY)
        if(result.body() != null)
        {
            Log.d("kkk","Response of Popular Movie : "+result.body().toString())
            mutableMovieDetailLiveData.postValue(result.body())
        }
    }
}