package com.zopsmart.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zopsmart.mymovies.ViewModels.PopularMovieViewFactory
import com.zopsmart.mymovies.ViewModels.PopularMovieViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.repository.PopularMovieRepository
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var popularMovieViewModel : PopularMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val movieService = RetrofitHelper.getInstance().create(apiInterface::class.java)
        val repository= PopularMovieRepository(movieService)

        popularMovieViewModel = ViewModelProvider(this,PopularMovieViewFactory(repository)).get(PopularMovieViewModel::class.java)

        popularMovieViewModel.popularMoviesLiveData.observe(this, Observer {
            Log.d("AppRunning",it.results.toString())
        })
    }
}