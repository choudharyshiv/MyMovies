package com.zopsmart.mymovies.api

import com.zopsmart.mymovies.model.MovieDetailModel
import com.zopsmart.mymovies.model.PopularMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface apiInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apikey : String) : Response<PopularMovieModel>

    @GET("/movie/{movieId}/{api_key}")
    suspend fun getMovieDetails(@Path("movieId") movieId : Int,@Path("api_key") apikey : String) : Response<MovieDetailModel>

}

//https://api.themoviedb.org/3/movie/299534?api_key=65db5aebb7dc29d77c7b00443904e829&language=en-US