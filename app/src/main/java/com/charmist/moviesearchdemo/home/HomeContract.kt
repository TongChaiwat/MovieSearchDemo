package com.charmist.moviesearchdemo.home

interface HomeContract {

    interface HomeView {

        fun getTextFromSearchBox(): String

        fun getHistoryList(): List<String>

        fun showHistory()

        fun showClearTextIcon(isShow: Boolean)

        fun showFavoriteColor(color: Int)

        fun navigateToMovieListPage(movieName: String, isFavorite: Boolean)

        fun updateHistory(historyList: List<String>)
    }

    interface HomePresenter {

        fun checkFavorite()

        fun checkTextEmpty()

        fun saveHistory()

    }
}
