package com.zopsmart.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.zopsmart.mymovies.ViewModels.MovieDetailViewFactory
import com.zopsmart.mymovies.ViewModels.MovieDetailViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.repository.MovieDetailRepository
import com.zopsmart.mymovies.repository.PopularMovieRepository

class MovieDetailActivity : AppCompatActivity() {

    lateinit var movieDetailViewModel: MovieDetailViewModel

    var movieId : Int  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail2)

        val movieService = RetrofitHelper.getInstance().create(apiInterface::class.java)
        val repository= MovieDetailRepository(movieService)


        if(intent != null)
        {
            movieId = intent.getIntExtra("movieId",0)
            Log.d("kkk","receive movie id : "+movieId)
        }

        movieDetailViewModel = ViewModelProvider(this , MovieDetailViewFactory(repository)).get(MovieDetailViewModel :: class.java)
        movieDetailViewModel.getMovieDetail(movieId)

        movieDetailViewModel.movieDetailLiveData.observe(this,{
            Log.d("kkk", "movie detail response : $it")
        })

    }



}