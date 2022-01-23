package com.zopsmart.mymovies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zopsmart.mymovies.api.ApiInterface
import com.zopsmart.mymovies.model.MovieDetailModel

class MovieDetailRepository(private val ApiInterface: ApiInterface) {


    private val mutableSpecificMovieLiveData = MutableLiveData<MovieDetailModel>()

    val specificMovieLiveData: LiveData<MovieDetailModel>
        get() = mutableSpecificMovieLiveData


    suspend fun getSpecificMovieDetails(movieId: Int) {
        val result = ApiInterface.getMovieDetails(movieId)
        Log.d(
            "sss", "result of repo : " + result.toString() + "result of body :"
                    + result.body()
        )
        if (result.body() != null) {
            Log.d("sss", "specific movie response : " + result.body())
            mutableSpecificMovieLiveData.postValue(result.body())
        }
    }

}