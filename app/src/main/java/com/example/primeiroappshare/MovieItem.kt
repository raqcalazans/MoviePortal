package com.example.primeiroappshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.primeiroappshare.databinding.MovieItemBinding

class MovieItem: AppCompatActivity() {
    private lateinit var binding: MovieItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieItem.setOnClickListener {
            val intent = Intent(this, DetailsMovieActivity::class.java)
            startActivity(intent)
        }
    }
}