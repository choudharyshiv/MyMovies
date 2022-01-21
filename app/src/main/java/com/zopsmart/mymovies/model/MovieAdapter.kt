package com.zopsmart.mymovies.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zopsmart.mymovies.MovieDetailActivity
import com.zopsmart.mymovies.databinding.ItemBinding

class MovieAdapter(private val context  : Context, private val popularMovieList : List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieVH>() {



    class MovieVH(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MovieVH(binding)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val popularMovie = popularMovieList[position]
        holder.binding.cvMovieTitle.text = popularMovie.title
        Glide
            .with(holder.itemView)
            .load("https://www.themoviedb.org/t/p/w500${popularMovie.posterPath}")
            .fitCenter()
            .into(holder.binding.cvIvMoviePoster)

        holder.itemView.setOnClickListener(){
            val intent : Intent = Intent(context, MovieDetailActivity::class.java)


            intent.putExtra("movieid",popularMovie.id)

           // intent.putExtra("poster",popularMovie.posterPath)
            startActivity(context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }


}