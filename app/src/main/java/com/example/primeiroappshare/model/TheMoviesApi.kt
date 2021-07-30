package com.example.primeiroappshare.model

import com.example.primeiroappshare.model.ApiConsts.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface TheMoviesApi {
    @GET("3/movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun listPopular(): Call<MovieList>
}







