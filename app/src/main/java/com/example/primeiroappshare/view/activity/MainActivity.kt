package com.example.primeiroappshare.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val ID_LIST = "id_list"
        const val ID_MOVIE = "id_movie"
        const val POPULAR = 0
        const val TOP_RATED = 1
        const val UPCOMING = 2
        const val FAVORITE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFavorite.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }

        binding.btnPopular.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(ID_LIST, POPULAR)
            startActivity(intent)
        }

        binding.btnTop.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(ID_LIST, TOP_RATED)
            startActivity(intent)
        }

        binding.btnUpcoming.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(ID_LIST, UPCOMING)
            startActivity(intent)
        }

        binding.btnFavorite.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(ID_LIST, FAVORITE)
            startActivity(intent)
        }
    }
}

