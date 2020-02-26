package com.charmist.moviesearchdemo.movie.movielist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charmist.moviesearchdemo.movie.moviedetail.MovieDetailActivity
import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Movie
import com.charmist.moviesearchdemo.utils.Constant
import com.charmist.moviesearchdemo.utils.extension.toast
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieListContract.MovieListView {

    private var movieName: String? = null
    private var isFavorite: Boolean = false
    private var isLoadMore: Boolean = false
    private lateinit var movieListPresenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        movieListPresenter = MovieListPresenter(this)
        initIntent(bundle = savedInstanceState ?: intent?.extras)
        initView()
    }

    private fun initView() {
        val adapter = MovieAdapter {
            navigateToMovieDetailPage(it, isFavorite)
        }
        rvMovie.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
        tvBack.setOnClickListener {
            finish()
        }
        if (isFavorite) {
            movieListPresenter.getMovieListFromDatabase()
        } else {
            movieListPresenter.getMovie(movieName ?: "", isLoadMore)
            rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount =
                        (recyclerView.layoutManager as LinearLayoutManager).childCount
                    val pastVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        movieListPresenter.checkPagination(movieName ?: "")
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun initIntent(bundle: Bundle?) {
        bundle?.getString(Constant.FIELD_MOVIE_NAME)?.also {
            movieName = it
        }
        bundle?.getBoolean(Constant.FIELD_FAVORITE)?.also {
            isFavorite = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Constant.FIELD_MOVIE_NAME, movieName)
        outState.putBoolean(Constant.FIELD_FAVORITE, isFavorite)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.MOVIE_DETAIL_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            finish()
        } else {
            if (isFavorite) {
                movieListPresenter.getMovieListFromDatabase()
            }
        }
    }

    override fun showMovie(movieList: MutableList<Movie>, isLoadMore: Boolean) {
        if (isFinishing) return
        if (isLoadMore) {
            (rvMovie.adapter as MovieAdapter).addMovieList(movieList)
        } else {
            (rvMovie.adapter as MovieAdapter).values = movieList
        }
    }


    override fun showLoading(isShow: Boolean) {
        if (isShow) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun navigateToMovieDetailPage(movie: Movie, isFavorite: Boolean) {
        startActivityForResult(
            MovieDetailActivity.createMovieIntent(this, movie, isFavorite),
            Constant.MOVIE_DETAIL_REQUEST_CODE
        )
    }

    override fun toastMsg(resId: Int) {
        toast(getString(resId))
    }

}
