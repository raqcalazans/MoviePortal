package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding
import com.example.primeiroappshare.model.MovieRepository

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private var pageApi: Int = 1
    private lateinit var adapterMovies: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterMovies = MoviesAdapter { id ->
            val intent = Intent(this, DetailsMovieActivity::class.java)
            intent.putExtra("id_api", id)
            startActivity(intent)
        }
        binding.recycleMovies.adapter = adapterMovies
        callApi()
        binding.btnSeeMore.setOnClickListener() {
            callApi()
        }
    }

    private fun callApi() {
        MovieRepository.getPopular(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }
}