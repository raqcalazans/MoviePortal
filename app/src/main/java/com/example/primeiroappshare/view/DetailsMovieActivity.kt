package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityDetailsMovieBinding

class DetailsMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getIntExtra("id_api", -1)

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }
    }
}