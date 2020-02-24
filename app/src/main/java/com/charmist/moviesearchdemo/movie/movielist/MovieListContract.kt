package com.charmist.moviesearchdemo.movie.movielist

import com.charmist.moviesearchdemo.model.Movie

interface MovieListContract {

    interface MovieListView {

        fun showMovie(movieList: MutableList<Movie>, isLoadMore: Boolean)

        fun showLoading(isShow: Boolean)

        fun navigateToMovieDetailPage(movie: Movie, isFavorite: Boolean)

        fun toastMsg(resId: Int)

    }

    interface MovieListPresenter {

        fun getMovie(name: String, isLoadMore: Boolean)

        fun getMovieListFromDatabase()

        fun checkPagination(movieName: String)

    }
}
