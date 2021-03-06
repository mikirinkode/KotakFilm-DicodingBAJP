package com.mikirinkode.kotakfilmlatihan.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.model.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}