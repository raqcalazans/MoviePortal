package com.example.primeiroappshare.model

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.primeiroappshare.model.ApiConsts.API_KEY
import com.example.primeiroappshare.model.ApiConsts.IDIOM
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    val moviesApi: TheMoviesApi = retrofit.create(TheMoviesApi::class.java)
    var database: AppDatabase? = null

    fun initDatabase(context: Context) {
        if(database == null) {
            database = Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()
        }
    }

    fun addFavorite(context: Context, movie: MovieModel) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                database?.movieDao()?.insertFavorite(movie)
            }
        }
    }

    fun removeFavorite(context: Context, movie: MovieModel) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                database?.movieDao()?.deleteFavorite(movie)
            }
        }
    }

    fun getFavorite(context: Context, callback: (List<MovieModel>) -> Unit) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorite = database?.movieDao()?.getAllFavorite()
                withContext(Dispatchers.Main) {
                    callback(listFavorite ?: listOf())
                }
            }
        }
    }

    fun getPopular(page: Int, callback: (List<MovieModel>) -> Unit){
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val callApi = moviesApi.listPopular(page = page)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        println("Caiu no onFailure")
                    }
                })
            }
        }
    }

    fun getTopRated(page: Int, callback: (List<MovieModel>) -> Unit){
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val callApi = moviesApi.listTopRated(page = page)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        println("Caiu no onFailure")
                    }
                })
            }
        }
    }

    fun getUpcoming(page: Int, callback: (List<MovieModel>) -> Unit){
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val callApi = moviesApi.listUpcoming(page = page)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        println("Caiu no onFailure")
                    }
                })
            }
        }
    }

    fun getMovie(callback: (MovieModel) -> Unit, id: Int){
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val callApi = moviesApi.getMovieById(id, API_KEY, IDIOM)
                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun searchMovie(searchItem: String, callback: (List<MovieModel>) -> Unit){
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val callApi = moviesApi.searchMovie(API_KEY, IDIOM, searchItem)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        response.body()?.let { movieList ->
                            callback(movieList.results)
                        }
                    }
                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        println("Caiu no onFailure")
                    }
                })
            }
        }
    }
}