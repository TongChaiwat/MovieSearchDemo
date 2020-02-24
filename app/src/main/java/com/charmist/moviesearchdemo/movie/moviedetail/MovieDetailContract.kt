package com.charmist.moviesearchdemo.movie.moviedetail

import com.charmist.moviesearchdemo.model.Movie

interface MovieDetailContract {

    interface MovieDetailView {

        fun showFavorite(isFavorite: Boolean)

    }

    interface MovieDetailPresenter {

        fun setFavorite(movie: Movie, isFavorite: Boolean)

    }
}
