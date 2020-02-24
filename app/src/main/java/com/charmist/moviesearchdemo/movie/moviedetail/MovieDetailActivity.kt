package com.charmist.moviesearchdemo.movie.moviedetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Movie
import com.charmist.moviesearchdemo.utils.Constant
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.MovieDetailView {
    private var movie: Movie? = null
    private var isFavorite = false
    private lateinit var movieDetailPresenter: MovieDetailPresenter

    companion object {
        fun createMovieIntent(context: Context, movie: Movie?, isFavorite: Boolean): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(Constant.FIELD_MOVIE, movie)
            intent.putExtra(Constant.FIELD_FAVORITE, isFavorite)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movieDetailPresenter = MovieDetailPresenter(this)
        initIntent(bundle = savedInstanceState ?: intent?.extras)
        initView()
    }

    private fun initView() {
        movie?.let { movie ->
            tvVote.text = getString(R.string.vote, movie.vote_average)
            tvTitle.text = movie.title
            tvDescription.text = movie.overview
            Glide.with(this)
                .load(Constant.BASE_IMAGE_URL + movie.poster_path)
                .error(R.drawable.ic_no_image)
                .into(ivPoster)
            tvFavorite.setOnClickListener {
                movieDetailPresenter.setFavorite(movie, isFavorite)
                isFavorite = !isFavorite
            }
        }
        tvBack.setOnClickListener {
            finish()
        }
        tvBackToSearch.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }
        showFavorite(isFavorite)
    }

    private fun initIntent(bundle: Bundle?) {
        bundle?.getParcelable<Movie>(Constant.FIELD_MOVIE)?.also {
            movie = it
        }
        bundle?.getBoolean(Constant.FIELD_FAVORITE)?.also {
            isFavorite = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constant.FIELD_MOVIE, movie)
        outState.putBoolean(Constant.FIELD_FAVORITE, isFavorite)
    }

    override fun showFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            tvFavorite.text = getString(R.string.un_favorite)
        } else {
            tvFavorite.text = getString(R.string.favorite)
        }
    }

}
