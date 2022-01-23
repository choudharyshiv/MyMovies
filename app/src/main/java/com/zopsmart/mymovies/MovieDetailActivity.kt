package com.zopsmart.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.zopsmart.mymovies.viewModels.MovieDetailViewFactory
import com.zopsmart.mymovies.viewModels.MovieDetailViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.ApiInterface
import com.zopsmart.mymovies.repository.MovieDetailRepository

class MovieDetailActivity : AppCompatActivity() {


    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail2)
        val movieService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val repository = MovieDetailRepository(movieService)

//use binding
        val moviePosterImageView: ImageView = findViewById(R.id.moviePosterImageView)
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsingToolBar)
        val releaseDateTextView: TextView = findViewById(R.id.releaseDateTextView)
        val movieTitleTextView: TextView = findViewById(R.id.movieTitleTextView)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val overviewTextView: TextView = findViewById(R.id.synopsisTextView)
        //val likeFab: FloatingActionButton = findViewById(R.id.likeFab)


        intent?.let {
            movieId = it.getIntExtra("movieId", 0)
            Log.d("kkk", "receive movie id : $movieId")
        }
        val movieDetailViewModel: MovieDetailViewModel = ViewModelProvider(
            this,
            MovieDetailViewFactory(repository)
        )[MovieDetailViewModel::class.java]
        movieDetailViewModel.getMovieDetail(movieId)

        movieDetailViewModel.movieDetailLiveData.observe(this, {
            Log.d("kkk", "movie detail response : $it")
            collapsingToolbar.title = it.title
            movieTitleTextView.text = it.title
            ratingBar.rating = (it.voteAverage / 2).toFloat()
            releaseDateTextView.text = it.releaseDate
            overviewTextView.text = it.overview

            Glide
                .with(this)
                .load("https://www.themoviedb.org/t/p/w500${it.posterPath}")
                .centerCrop()
                .into(moviePosterImageView)
        })


    }


}