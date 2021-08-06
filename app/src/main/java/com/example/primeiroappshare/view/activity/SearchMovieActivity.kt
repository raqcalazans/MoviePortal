package com.example.primeiroappshare.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.primeiroappshare.databinding.ActivitySearchMovieBinding
import com.example.primeiroappshare.model.MovieRepository
import com.example.primeiroappshare.view.MoviesAdapter
import com.example.primeiroappshare.view.activity.MainActivity.Companion.ID_MOVIE

class SearchMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchMovieBinding
    private lateinit var adapterMovies: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        setupList()
        setupQuery()

        binding.btnBackSearch.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupQuery() {
        binding.btnSearchAct.setOnClickListener {
            val searchItem: String = binding.searchView.text.toString()
            if (searchItem.isNullOrBlank()) return@setOnClickListener Toast.makeText(
                this,
                "Escreva o nome de um filme para pesquisar",
                Toast.LENGTH_LONG
            ).show()
            else filterList(searchItem)
        }
    }

    private fun filterList(searchItem: String) {
        MovieRepository.searchMovie(searchItem) { listSearch ->
            adapterMovies.addItemList(listSearch)
        }
    }

    private fun setupList() {
        adapterMovies = MoviesAdapter({ id ->
            val intent = Intent(this, SearchMovieActivity::class.java)
            intent.putExtra(ID_MOVIE, id)
            startActivity(intent)
        }) { movie, isChecked ->
            movie.is_favorite = isChecked
            if (isChecked) MovieRepository.addFavorite(this, movie)
            else MovieRepository.removeFavorite(this, movie)
        }
        startActivity(intent)
        binding.searchRecycleMovies.adapter = adapterMovies
    }

    private fun initLayout() {
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}