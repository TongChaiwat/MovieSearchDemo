package com.charmist.moviesearchdemo.model

import android.content.Context
import androidx.room.Room
import com.charmist.moviesearchdemo.utils.Constant

object Database {
    private lateinit var movieDatabase: MovieDatabase

    fun init(context: Context) {
        movieDatabase =
            Room.databaseBuilder(context, MovieDatabase::class.java, Constant.DATABASE_NAME).build()
    }

    fun get(): MovieDatabase = movieDatabase
}