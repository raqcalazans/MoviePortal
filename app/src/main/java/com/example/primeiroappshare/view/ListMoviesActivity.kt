package com.example.primeiroappshare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding
import com.example.primeiroappshare.model.MovieRepository
import com.example.primeiroappshare.view.MainActivity.Companion.ID_LIST

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private var pageApi: Int = 1
    private lateinit var adapterMovies: MoviesAdapter

    companion object {
        const val POPULAR = 0
        const val TOP_RATED = 1
        const val UPCOMING = 2
        const val LATEST = 3
    }

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
        val idList = intent.getIntExtra(ID_LIST, -1)

        when (idList) {
            0 -> callPopular()
            1 -> callTop()
            2 -> callUpcoming()
            3 -> callLatest()
        }

        binding.btnSeeMore.setOnClickListener {
            when (idList) {
                0 -> callPopular()
                1 -> callTop()
                2 -> callUpcoming()
                3 -> callLatest()
            }
        }
    }

    private fun callPopular() {
        MovieRepository.getPopular(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }

    private fun callTop() {
        MovieRepository.getTopRated(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }

    private fun callUpcoming() {
        MovieRepository.getUpcoming(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }

    private fun callLatest() {
        MovieRepository.getLatest(pageApi) { list ->
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.recycleMovies.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }
}