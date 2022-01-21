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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zopsmart.mymovies.ViewModels.MovieDetailViewFactory
import com.zopsmart.mymovies.ViewModels.MovieDetailViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.repository.MovieDetailRepository

class MovieDetailActivity : AppCompatActivity() {

    lateinit var movieDetailViewModel: MovieDetailViewModel
    lateinit var movieTitleTextView : TextView
    lateinit var likeFab : FloatingActionButton
    lateinit var moviePosterImageView : ImageView
    lateinit var collapsingToolbar: CollapsingToolbarLayout
    lateinit var releaseDateTextView : TextView
    lateinit var ratingBar: RatingBar
    lateinit var overviewTextView: TextView

    var movieId : Int  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail2)
        val movieService = RetrofitHelper.getInstance().create(apiInterface::class.java)
        val repository= MovieDetailRepository(movieService)


        moviePosterImageView = findViewById(R.id.moviePosterImageView)
        collapsingToolbar = findViewById(R.id.collapsingToolBar)
        releaseDateTextView = findViewById(R.id.releaseDateTextView)
        movieTitleTextView = findViewById(R.id.movieTitleTextView)
        ratingBar = findViewById(R.id.ratingBar)
        overviewTextView = findViewById(R.id.synopsisTextView)
        likeFab = findViewById(R.id.likeFab)



        if(intent != null)
        {
            movieId = intent.getIntExtra("movieId",0)
            Log.d("kkk","receive movie id : "+movieId)
        }

        movieDetailViewModel = ViewModelProvider(this , MovieDetailViewFactory(repository)).get(MovieDetailViewModel :: class.java)
        movieDetailViewModel.getMovieDetail(movieId)

        movieDetailViewModel.movieDetailLiveData.observe(this,{
            Log.d("kkk", "movie detail response : $it")
            collapsingToolbar.title = it.title
            movieTitleTextView.text = it.title
            ratingBar.rating = (it.voteAverage / 2).toFloat()
            releaseDateTextView.text = it.releaseDate
            overviewTextView.text = it.overview

            Glide
                .with(this)
                .load("https://www.themoviedb.org/t/p/w500${it.posterPath}")
                .fitCenter()
                .into(moviePosterImageView)
        })


    }


}