package com.zopsmart.mymovies

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zopsmart.mymovies.ViewModels.MovieDetailViewFactory
import com.zopsmart.mymovies.ViewModels.MovieDetailViewModel
import com.zopsmart.mymovies.ViewModels.PopularMovieViewModel
import com.zopsmart.mymovies.api.RetrofitHelper
import com.zopsmart.mymovies.api.apiInterface
import com.zopsmart.mymovies.databinding.ActivityMovieDetailBinding
import com.zopsmart.mymovies.model.MovieAdapter
import com.zopsmart.mymovies.model.MovieDetailModel
import com.zopsmart.mymovies.repository.MovieDetailRepository


class MovieDetailActivity : AppCompatActivity() {

private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var textView : TextView
    lateinit var movieDetailViewmodel : MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMovieDetailBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbarid))
        binding.toolbarLayout.title = title
        binding.likebuttonid.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }



            val MovieId= intent.getIntExtra("movieid",86311)



        textView = findViewById(R.id.discriptionid)


        val movieService = RetrofitHelper.getInstance().create(apiInterface::class.java)
        val repository= MovieDetailRepository(movieService)
        movieDetailViewmodel =  ViewModelProvider(this, MovieDetailViewFactory(repository)).get(MovieDetailViewModel::class.java)
        movieDetailViewmodel.getMovieDetail(MovieId)


        movieDetailViewmodel.MoviesDetailLiveData.observe(this, Observer {
            Log.d("sss",it.toString())

        })

        //textView.text=kk
       // binding.toolbarLayout.title = MovieId.toString()
    }
    fun BindUi(it: MovieDetailModel)
    {
        textView.text=it.title
        binding.toolbarLayout.title =it.title.toString()
    }
}
