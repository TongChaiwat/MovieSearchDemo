package com.charmist.moviesearchdemo.movie.moviedetail

import com.charmist.moviesearchdemo.model.Database
import com.charmist.moviesearchdemo.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailPresenter(private var view: MovieDetailContract.MovieDetailView) :
    MovieDetailContract.MovieDetailPresenter {

    override fun setFavorite(movie: Movie, isFavorite: Boolean) {
        if (isFavorite) {
            view.showFavorite(false)
            GlobalScope.launch(Dispatchers.IO) {
                Database.get().movieDao().deleteMovie(movie)
            }
        } else {
            view.showFavorite(true)
            GlobalScope.launch(Dispatchers.IO) {
                Database.get().movieDao().insertMovie(movie)
            }
        }
    }
}