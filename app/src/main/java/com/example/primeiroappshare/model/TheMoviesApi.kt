package com.example.primeiroappshare.model

import com.example.primeiroappshare.model.ApiConsts.API_KEY
import com.example.primeiroappshare.model.ApiConsts.IDIOM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") idioma: String = IDIOM,
        @Query("query") searchQueryApi: String
    ): Call<MovieList>

    @GET("movie/popular")
    fun listPopular(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") idiom: String = IDIOM,
        @Query("page") page: Int
    ): Call<MovieList>

    @GET("movie/top_rated")
    fun listTopRated(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") idiom: String = IDIOM,
        @Query("page") page: Int
    ): Call<MovieList>

    @GET("movie/upcoming")
    fun listUpcoming(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") idiom: String = IDIOM,
        @Query("page") page: Int
    ): Call<MovieList>

    @GET("movie/{idMovie}")
    fun getMovieById(
        @Path("idMovie") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") idiom: String = IDIOM
    ): Call<MovieModel>
}








