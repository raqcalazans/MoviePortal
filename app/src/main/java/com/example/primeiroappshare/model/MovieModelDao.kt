package com.example.primeiroappshare.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Insert
    fun insertFavorite(movie: MovieModel)

    @Delete
    fun deleteFavorite(movie: MovieModel)

    @Query("SELECT * FROM favorites")
    fun getAllFavorite() : List<MovieModel>
}