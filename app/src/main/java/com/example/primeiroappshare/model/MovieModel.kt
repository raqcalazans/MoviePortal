package com.example.primeiroappshare.model

data class MovieModel(val title: String, val id: Int, val poster_path: String, val overview: String, val release_date: String, val vote_average: Double, val genres: List<GenreModel?>)