package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.primeiroappshare.databinding.ActivityDetailsMovieBinding
import com.example.primeiroappshare.model.MovieRepository

class DetailsMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsMovieBinding
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id_api", -1)

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }

        MovieRepository.getMovie({
            binding.progressBar.visibility = View.GONE
            binding.textBackground.visibility = View.VISIBLE
            binding.nameMovie.visibility = View.VISIBLE
            binding.nameMovie.text = it.title
            binding.imdbMovie.visibility = View.VISIBLE
            binding.imdbMovie.text = "IMDb: ${it.vote_average}"
            binding.releaseDateMovie.visibility = View.VISIBLE
            binding.releaseDateMovie.text = "Release date: ${it.release_date}"
            binding.genreMovie.visibility = View.VISIBLE
            binding.genreMovie.text = "Genre: ${it.genres.toString()}"
            binding.overviewMovie.visibility = View.VISIBLE
            binding.overviewMovie.text = it.overview
            binding.posterMovie.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(binding.posterMovie)
        }, id)
    }
}



