package com.zopsmart.mymovies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

     const val API_KEY = "65db5aebb7dc29d77c7b00443904e829"

    fun getInstance() : Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}