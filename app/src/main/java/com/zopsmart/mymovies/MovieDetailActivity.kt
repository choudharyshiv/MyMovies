package com.zopsmart.mymovies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zopsmart.mymovies.viewModels.MovieDetailViewFactory
import com.zopsmart.mymovies.viewModels.MovieDetailViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.ApiInterface
import com.zopsmart.mymovies.database.MovieDatabase
import com.zopsmart.mymovies.entity.MovieLike
import com.zopsmart.mymovies.repository.MovieDetailRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
class MovieDetailActivity : AppCompatActivity() {
    private var isLike: Boolean = false
    private lateinit var likeButton: FloatingActionButton
    private lateinit var movieDatabase: MovieDatabase
    private var movieId: Int = 0

    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail2)

        movieDatabase = Room.databaseBuilder(this,MovieDatabase::class.java,
            "MovieDataBase").build()

        val movieService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val repository = MovieDetailRepository(movieService)
        val moviePosterImageView: ImageView = findViewById(R.id.moviePosterImageView)
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsingToolBar)
        val releaseDateTextView: TextView = findViewById(R.id.releaseDateTextView)
        val movieTitleTextView: TextView = findViewById(R.id.movieTitleTextView)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val overviewTextView: TextView = findViewById(R.id.synopsisTextView)
        likeButton = findViewById(R.id.likeFab)

        intent?.let {
            movieId = it.getIntExtra("movieId", 0)
            Log.d("kkk", "receive movie id : $movieId")
        }


        movieDatabase.movieLikeDao().getMovieRow(movieId).observe(this, {
            if(it!=null)
                isLike = it.isLike
            if(isLike)
            {
                likeButton.backgroundTintList = applicationContext.resources.
                getColorStateList(R.color.actionBarGreenColor)
            }
        })
       //OnLikeShow(movieId)

        likeButton.setOnClickListener{
            //change the value in the database
            if(isLike==false) {
                lifecycleScope.launch(Dispatchers.IO) {
                    likeButton.backgroundTintList = applicationContext.resources.
                    getColorStateList(R.color.actionBarGreenColor)
                    movieDatabase.movieLikeDao().insertMovieLike(MovieLike(movieId, true))


                }
            }
            else{
                lifecycleScope.launch(Dispatchers.IO) {
                    likeButton.backgroundTintList = applicationContext.resources.
                    getColorStateList(R.color.design_default_color_background)
                    movieDatabase.movieLikeDao().insertMovieLike(MovieLike(movieId, false))

                }
            }

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










