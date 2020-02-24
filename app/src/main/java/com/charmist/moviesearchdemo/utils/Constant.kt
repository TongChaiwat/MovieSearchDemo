package com.charmist.moviesearchdemo.utils

object Constant {

    // Field
    const val FIELD_MOVIE_NAME = "movie_name"
    const val FIELD_MOVIE = "movie"
    const val FIELD_FAVORITE = "favorite"

    // Request code
    const val MOVIE_LIST_REQUEST_CODE = 0x111
    const val MOVIE_DETAIL_REQUEST_CODE = 0x112

    // Request headers
    const val API_KEY_REQUEST_HEADER = "api-key"

    // API
    const val API_KEY = "2f70b177e4ba26509a63e4bd9e23bbdcb6634d60"
    const val API_TIME_OUT = 10L   // 10 seconds

    // API PATH
    const val BASE_URL = "http://scb-movies-api.herokuapp.com/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w92/"
    const val MOVIE_PATH = "/api/movies/search"

    // Database
    const val DATABASE_NAME = "movie.db"

}