package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.primeiroappshare.R
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding
import com.example.primeiroappshare.model.MovieRepository
import com.example.primeiroappshare.view.DetailsMovieActivity.Companion.ID_MOVIE
import com.example.primeiroappshare.view.MainActivity.Companion.ID_LIST

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private var pageApi: Int = 1
    private lateinit var adapterMovies: MoviesAdapter

    companion object {
        const val POPULAR = 0
        const val TOP_RATED = 1
        const val UPCOMING = 2
        const val FAVORITE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idList = intent.getIntExtra(ID_LIST, -1)
        when (idList) {
            0 -> callPopular()
            1 -> callTop()
            2 -> callUpcoming()
            3 -> callFavorite()
            else -> Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show()
        }

        adapterMovies = MoviesAdapter({ id ->
            val intent = Intent(this, DetailsMovieActivity::class.java)
            intent.putExtra(ID_MOVIE, id)
            intent.putExtra(ID_LIST, idList)
            startActivity(intent)
        }, { movie, isFavorite ->
            if(isFavorite) MovieRepository.addFavorite(this, movie)
            else MovieRepository.removeFavorite(this, movie)
        })

        binding.recycleMovies.adapter = adapterMovies

        binding.btnArrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSeeMore.setOnClickListener {
            when (idList) {
                0 -> callPopular()
                1 -> callTop()
                2 -> callUpcoming()
                3 -> callFavorite()
                else -> Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callFavorite() {
        MovieRepository.getFavorite(this) { list ->
            adapterMovies.addMovies(list)
            binding.progressBar.visibility = View.GONE
            binding.linearHeader.visibility = View.VISIBLE
            binding.listName.text = "Favorites Movies"
            binding.recycleMovies.visibility = View.VISIBLE
        }
    }

    private fun callPopular() {
        MovieRepository.getPopular(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.linearHeader.visibility = View.VISIBLE
            binding.listName.text = getString(R.string.title_popular)
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }

    private fun callTop() {
        MovieRepository.getTopRated(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.linearHeader.visibility = View.VISIBLE
            binding.listName.text = getString(R.string.title_top)
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }

    private fun callUpcoming() {
        MovieRepository.getUpcoming(pageApi) { list ->
            // buscar ids dos favoritos var list = idsFavorite()
            // checkFavorite(list)
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.linearHeader.visibility = View.VISIBLE
            binding.listName.text = getString(R.string.title_upcoming)
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }
}