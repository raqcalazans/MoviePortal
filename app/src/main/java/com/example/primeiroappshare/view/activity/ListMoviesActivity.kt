package com.example.primeiroappshare.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.primeiroappshare.R
import com.example.primeiroappshare.databinding.ActivityListMoviesBinding
import com.example.primeiroappshare.model.MovieModel
import com.example.primeiroappshare.model.MovieRepository
import com.example.primeiroappshare.view.MoviesAdapter
import com.example.primeiroappshare.view.activity.MainActivity.Companion.ID_LIST
import com.example.primeiroappshare.view.activity.MainActivity.Companion.ID_MOVIE
import com.example.primeiroappshare.view.activity.MainActivity.Companion.SEARCH

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private var pageApi: Int = 1
    private lateinit var adapterMovies: MoviesAdapter

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
        }) { movie, isChecked ->
            movie.is_favorite = isChecked
            if (isChecked) MovieRepository.addFavorite(this, movie)
            else MovieRepository.removeFavorite(this, movie)
        }

        binding.recycleMovies.adapter = adapterMovies

        binding.btnArrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        binding.btnSearch.setOnClickListener {
//            println("Clicou no botão de pesquisa")
//            val intent = Intent(this, SearchMovieActivity::class.java)
//            intent.putExtra(ID_LIST, SEARCH)
//            startActivity(intent)
//        }

        binding.btnSeeMore.setOnClickListener {
            when (idList) {
                0 -> callPopular()
                1 -> callTop()
                2 -> callUpcoming()
                else -> Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callPopular() {
        MovieRepository.getPopular(pageApi) { list ->
            updateListWithFavorites(list)
            binding.listName.text = getString(R.string.title_popular)
        }
    }

    private fun callTop() {
        MovieRepository.getTopRated(pageApi) { list ->
            updateListWithFavorites(list)
            binding.listName.text = getString(R.string.title_top)
        }
    }

    private fun callUpcoming() {
        MovieRepository.getUpcoming(pageApi) { list ->
            updateListWithFavorites(list)
            binding.listName.text = getString(R.string.title_upcoming)
        }
    }

    private fun callFavorite() {
        MovieRepository.getFavorite(this) { list ->
            if(list.isEmpty()) {
                binding.progressBar.visibility = View.GONE
                binding.linearHeader.visibility = View.VISIBLE
                binding.listName.text = "Favorite Movies"
                binding.warningText.visibility = View.VISIBLE
            } else {
                list.forEach{ movie ->
                    movie.is_favorite = true
                }
                adapterMovies.addMovies(list)
                binding.progressBar.visibility = View.GONE
                binding.linearHeader.visibility = View.VISIBLE
                binding.listName.text = "Favorite Movies"
                binding.scrollViewList.visibility = View.VISIBLE
            }
        }
    }

    private fun updateListWithFavorites(list: List<MovieModel>) {
        MovieRepository.getFavorite(this) {
            list.forEach { movie ->
                movie.is_favorite = it.any { model ->
                    movie.id == model.id
                }
            }
            adapterMovies.addMovies(list)
            pageApi++
            binding.progressBar.visibility = View.GONE
            binding.linearHeader.visibility = View.VISIBLE
            binding.scrollViewList.visibility = View.VISIBLE
            binding.btnSeeMore.visibility = View.VISIBLE
        }
    }
}