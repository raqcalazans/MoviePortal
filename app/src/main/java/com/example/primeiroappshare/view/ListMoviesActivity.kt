package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding
import com.example.primeiroappshare.model.MovieRepository

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterMovies = MoviesAdapter { id ->
            val intent = Intent(this, DetailsMovieActivity::class.java)
            intent.putExtra("id_api", id)
            startActivity(intent)
        }

        binding.recycleMovies.adapter = adapterMovies

        MovieRepository.getPopular { list ->
            adapterMovies.addMovies(list)
        }
    }
}