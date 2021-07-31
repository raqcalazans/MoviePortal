package com.example.primeiroappshare.model

import com.example.primeiroappshare.model.ApiConsts.API_KEY
import com.example.primeiroappshare.model.ApiConsts.IDIOM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("popular")
    fun listPopular(@Query("api_key") apiKey: String = API_KEY,
                    @Query("language") idiom: String = IDIOM,
                    @Query("page") page: Int): Call<MovieList>

    @GET("3/movie/top_rated")
    fun listTopRated(@Query("api_key") apiKey: String = API_KEY,
                    @Query("language") idiom: String = IDIOM,
                    @Query("page") page: Int): Call<MovieList>

    @GET("upcoming")
    fun listUpcoming(@Query("api_key") apiKey: String = API_KEY,
                     @Query("language") idiom: String = IDIOM,
                     @Query("page") page: Int): Call<MovieList>

    @GET("latest")
    fun listLatest(@Query("api_key") apiKey: String = API_KEY,
                   @Query("language") idiom: String = IDIOM,
                   @Query("page") page: Int): Call<MovieList>

    @GET("{idMovie}")
    fun getMovieById(@Query("api_key") apiKey: String = API_KEY,
                     @Query("language") idiom: String = IDIOM,
                     @Query("idMovie") id: Int): Call<MovieModel>
}








