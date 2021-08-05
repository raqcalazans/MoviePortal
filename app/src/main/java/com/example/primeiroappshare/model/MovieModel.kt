package com.example.primeiroappshare.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class MovieModel(
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @Ignore
    val genres: List<GenreModel?> = listOf(),
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean = false
) {
    constructor(
        is_favorite: Boolean, runtime: Int, adult: Boolean, vote_average: Double, release_date: String,
        overview: String, poster_path: String, id: Int, title: String
    ) : this(title, id, poster_path, overview, release_date, vote_average, listOf(), adult, runtime, is_favorite)
}

