package com.charmist.moviesearchdemo.movie.movielist

import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Apis
import com.charmist.moviesearchdemo.model.Database
import com.charmist.moviesearchdemo.utils.extension.enqueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private var view: MovieListContract.MovieListView) :
    MovieListContract.MovieListPresenter {

    private var isLoading = false
    private var pagination = 1
    private var paginationTotal = 1

    override fun getMovie(name: String, isLoadMore: Boolean) {
        isLoading = true
        view.showLoading(true)
        Apis.api.getMovieByName(name, pagination).enqueue({
            pagination++
            paginationTotal = it?.total_pages ?: 1
            view.showMovie(it?.results ?: ArrayList(), isLoadMore)
            view.showLoading(false)
            if (it?.results?.isEmpty() == true) {
                view.toastMsg(R.string.movie_empty)
            }
            isLoading = false

        }, {
            view.showLoading(false)
            view.toastMsg(R.string.label_error_message)
            isLoading = false
        })

    }

    override fun getMovieListFromDatabase() {
        GlobalScope.launch(Dispatchers.Main) {
            val movieList = withContext(Dispatchers.IO) {
                Database.get().movieDao().getAllMovie()
            }
            view.showMovie(movieList, false)
            view.showLoading(false)
        }
    }

    override fun checkPagination(movieName: String) {
        if (pagination <= paginationTotal) {
            if (!isLoading) {
                getMovie(movieName, true)
            }
        }
    }

}