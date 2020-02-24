package com.charmist.moviesearchdemo.home

import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(private var view: HomeContract.HomeView) : HomeContract.HomePresenter {

    override fun checkFavorite() {
        GlobalScope.launch(Dispatchers.Main) {
            val movieList = withContext(Dispatchers.IO) {
                Database.get().movieDao().getAllMovie()
            }
            if (movieList.size > 0) {
                view.showFavoriteColor(R.color.colorBlue)

            } else {
                view.showFavoriteColor(R.color.colorGrey)
            }
        }
    }

    override fun checkTextEmpty() {
        if (view.getTextFromSearchBox().isNotEmpty()) {
            view.showClearTextIcon(true)
        } else {
            view.showClearTextIcon(false)
        }
    }

    override fun saveHistory() {
        val historyList = view.getHistoryList()
        var historyResult = listOf(view.getTextFromSearchBox()) + historyList
        if (historyResult.size > 5) {
            historyResult = historyList.subList(0, 5)
        }
        view.updateHistory(historyResult)
        view.showHistory()
        view.navigateToMovieListPage(view.getTextFromSearchBox(), false)
    }

}