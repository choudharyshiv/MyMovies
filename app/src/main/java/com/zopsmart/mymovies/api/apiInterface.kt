package com.zopsmart.mymovies.api

import com.zopsmart.mymovies.model.PopularMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface apiInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apikey : String) : Response<PopularMovieModel>
}