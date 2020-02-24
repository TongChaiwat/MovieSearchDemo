package com.charmist.moviesearchdemo

import com.charmist.moviesearchdemo.model.Movie
import com.charmist.moviesearchdemo.movie.moviedetail.MovieDetailContract
import com.charmist.moviesearchdemo.movie.moviedetail.MovieDetailPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MovieDetailActivityTest {

    @Mock
    private lateinit var view: MovieDetailContract.MovieDetailView
    private lateinit var presenter: MovieDetailPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MovieDetailPresenter(view)
    }

    @Test
    fun `Set Favorite`() {
        presenter.setFavorite(Movie(), false)
        verify(view).showFavorite(true)
    }

    @Test
    fun `Set UnFavorite`() {
        presenter.setFavorite(Movie(), true)
        verify(view).showFavorite(false)
    }

}
