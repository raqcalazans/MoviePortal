package com.example.primeiroappshare.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MovieModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao() : MovieModelDao
}