package com.charmist.moviesearchdemo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Movie::class],
    version = 1
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}