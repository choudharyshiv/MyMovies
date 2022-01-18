package com.zopsmart.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zopsmart.mymovies.ViewModels.PopularMovieViewFactory
import com.zopsmart.mymovies.ViewModels.PopularMovieViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.databinding.ActivityMainBinding
import com.zopsmart.mymovies.model.Movie
import com.zopsmart.mymovies.model.MovieAdapter
import com.zopsmart.mymovies.model.PopularMovieModel
import com.zopsmart.mymovies.repository.PopularMovieRepository

class MainActivity : AppCompatActivity() {

    lateinit var popularMovieViewModel : PopularMovieViewModel

    lateinit var recylcerView : RecyclerView

    lateinit  var movieAdapter : MovieAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init the recycler view and other views
        recylcerView = findViewById(R.id.rv_movie_list)
        var gridLayoutManager = GridLayoutManager(this , 3 ,GridLayoutManager.VERTICAL , false )
        recylcerView.layoutManager = gridLayoutManager


        val movieService = RetrofitHelper.getInstance().create(apiInterface::class.java)
        val repository= PopularMovieRepository(movieService)

        popularMovieViewModel = ViewModelProvider(this,PopularMovieViewFactory(repository)).get(PopularMovieViewModel::class.java)

        popularMovieViewModel.popularMoviesLiveData.observe(this, Observer {
            Log.d("AppRunning",it.results.toString())
            var popularMovieList = it.results
            movieAdapter = MovieAdapter(this , popularMovieList)
            recylcerView.adapter = movieAdapter
        })


    }





}