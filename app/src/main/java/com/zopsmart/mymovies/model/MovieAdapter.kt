package com.zopsmart.mymovies.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }


}