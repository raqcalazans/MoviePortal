package com.example.primeiroappshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterMovies = MoviesAdapter {
            val intent = Intent(this, DetailsMovieActivity::class.java)
            startActivity(intent)
        }
        binding.recycleMovies.adapter = adapterMovies

        val list = List(10) {
            "Interestelar $it"
        }
        adapterMovies.addMovies(list)
    }

}