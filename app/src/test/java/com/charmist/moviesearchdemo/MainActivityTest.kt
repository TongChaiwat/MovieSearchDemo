package com.charmist.moviesearchdemo

import com.charmist.moviesearchdemo.home.HomeContract
import com.charmist.moviesearchdemo.home.HomePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainActivityTest {

    @Mock
    private lateinit var view: HomeContract.HomeView
    private lateinit var presenter: HomePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(view)
    }

    @Test
    fun `Should hide icon when box is empty`() {
        `when`(view.getTextFromSearchBox()).thenReturn("")
        presenter.checkTextEmpty()
        verify(view).showClearTextIcon(false)
    }

    @Test
    fun `Save history List`() {
        `when`(view.getHistoryList()).thenReturn(emptyList())
        presenter.saveHistory()
        verify(view).showHistory()
    }

}
