package com.charmist.moviesearchdemo.model

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_list")
    fun getAllMovie(): MutableList<Movie>

    @Query("SELECT * FROM movie_list WHERE id =:id LIMIT 1")
    fun getMovieById(id: Int): MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(vararg movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_list")
    fun deleteAll()

    @Update
    fun updateMovie(movie: Movie)
}