package com.zopsmart.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zopsmart.mymovies.api.ApiInterface
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.databinding.ActivityMainBinding
import com.zopsmart.mymovies.model.MovieAdapter
import com.zopsmart.mymovies.repository.PopularMovieRepository
import com.zopsmart.mymovies.viewModels.PopularMovieViewFactory
import com.zopsmart.mymovies.viewModels.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init the recycler view and other views
        val recylcerView: RecyclerView = findViewById(R.id.rv_movie_list)
        val gridLayoutManager = GridLayoutManager(
            this, 3,
            GridLayoutManager.VERTICAL, false
        )
        recylcerView.layoutManager = gridLayoutManager

        val movieService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val repository = PopularMovieRepository(movieService)

        val popularMovieViewModel: PopularMovieViewModel = ViewModelProvider(
            this,
            PopularMovieViewFactory(repository)
        )[PopularMovieViewModel::class.java]

        popularMovieViewModel.popularMoviesLiveData.observe(this, {
            Log.d("AppRunning", it.results.toString())
            val popularMovieList = it.results
            val movieAdapter = MovieAdapter(this, popularMovieList)
            recylcerView.adapter = movieAdapter
        })


    }
}




