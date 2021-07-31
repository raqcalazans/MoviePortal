package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.primeiroappshare.databinding.ActivityDetailsMovieBinding
import com.example.primeiroappshare.model.MovieRepository

class DetailsMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id_api", -1)

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }
            MovieRepository.getMovie({
                binding.sinopseFilme.text = it.overview
                binding.nomeFilme.text = it.title
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                    .into(binding.posterFilme)
            }, id)
    }
}