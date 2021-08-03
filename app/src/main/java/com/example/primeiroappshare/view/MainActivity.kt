package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityMainBinding
import com.example.primeiroappshare.view.ListMoviesActivity.Companion.LATEST
import com.example.primeiroappshare.view.ListMoviesActivity.Companion.POPULAR
import com.example.primeiroappshare.view.ListMoviesActivity.Companion.TOP_RATED
import com.example.primeiroappshare.view.ListMoviesActivity.Companion.UPCOMING

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val ID_LIST: String = "id_list"
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
            intent.putExtra(Companion.ID_LIST, POPULAR)
            startActivity(intent)
        }

        binding.btnTop.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(Companion.ID_LIST, TOP_RATED)
            startActivity(intent)
        }

        binding.btnUpcoming.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(Companion.ID_LIST, UPCOMING)
            startActivity(intent)
        }

        binding.btnLatest.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            intent.putExtra(Companion.ID_LIST, LATEST)
            startActivity(intent)
        }
    }
}

