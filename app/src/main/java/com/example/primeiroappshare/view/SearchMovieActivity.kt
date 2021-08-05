package com.example.primeiroappshare.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.model.MovieRepository

class SearchMovieActivity : AppCompatActivity() {
    private lateinit var binding: ListMoviesActivity
    private lateinit var adapterMovie: MoviesAdapter

    companion object {
        const val ID_MOVIE = "br.com.localiza.view.SearchListActivity.idMovie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initLayout()
//        setupList()
//        setupQuery()
    }

//    private fun setupQuery() {
//        binding.searchButton.setOnClickListener {
//            val searchItem: String = binding.searchBar.query.toString()
//            if (searchItem.isNullOrBlank()) return@setOnClickListener
//            filterList(searchItem)
//        }
//    }
//
//    private fun filterList(searchItem: String) {
//        MovieRepository.searchMovie(searchItem) { listSearch ->
//            adapterMovie.addItemList(listSearch)
//        }
//    }
//
//    private fun setupList() {
//        adapterMovie = MovieAdapter {
//            val details = Intent(this, DetailsActivity::class.java)
//            details.putExtra(SearchListActivity.ID_MOVIE, it)
//            startActivity(details)
//        }
//
//        binding.searchMoviesList.adapter = adapterMovie
//    }
//
//    private fun initLayout() {
//        binding = ListMoviesActivity.inflate(layoutInflater)
//        val view = bindingSearch.root
//        setContentView(view)
//    }
}