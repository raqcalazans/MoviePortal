package com.example.primeiroappshare.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.primeiroappshare.databinding.MovieItemBinding
import com.example.primeiroappshare.model.MovieModel

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val movieClickListener:(Int) -> Unit) :
    RecyclerView.Adapter<MoviesViewHolder>() {
    val movieList: MutableList<MovieModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.binding.movieTitle.text = item.title
        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.binding.posterMovieList)
        holder.binding.movieItem.setOnClickListener {
            movieClickListener(item.id)
        }
    }

    override fun getItemCount() = movieList.size

    fun addMovies(list: List<MovieModel>) {
        val firstItem = movieList.size
        movieList.addAll(list)
        notifyItemRangeInserted(firstItem, list.size)
    }
}