package com.charmist.moviesearchdemo.model

import com.charmist.moviesearchdemo.utils.Constant
import retrofit2.Call
import retrofit2.http.*

interface MovieApi {

    @GET(Constant.MOVIE_PATH)
    fun getMovieByName(@Query("query") limit: String, @Query("page") offset: Int): Call<MovieResponse>

}