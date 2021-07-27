package com.example.primeiroappshare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primeiroappshare.databinding.MovieItemBinding

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val movieClickListener:() -> Unit) :
    RecyclerView.Adapter<MoviesViewHolder>() {
    val movieList: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item: String = movieList[position]
        holder.binding.filmeTitulo.text = item
        holder.binding.movieItem.setOnClickListener {
            movieClickListener()
        }
    }

    override fun getItemCount() = movieList.size

    fun addMovies(list: List<String>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}