package com.charmist.moviesearchdemo.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.charmist.moviesearchdemo.movie.movielist.MovieListActivity

import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.utils.Constant
import com.charmist.moviesearchdemo.utils.SharedPreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeContract.HomeView {

    private lateinit var homePresenter: HomePresenter
    private val sharedPreferenceHelper: SharedPreferenceHelper by lazy {
        SharedPreferenceHelper.getInstance(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homePresenter = HomePresenter(this)
        initView()
    }

    private fun initView() {
        tvFavorite.setOnClickListener {
            navigateToMovieListPage("", true)
        }
        ivClearText.setOnClickListener {
            et_search.text.clear()
        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                homePresenter.checkTextEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (et_search.text.toString().isNotEmpty()) {
                    homePresenter.saveHistory()
                }
                true
            } else {
                false
            }
        }
        val adapter = HistoryAdapter {
            navigateToMovieListPage(it, false)
        }
        rvHistory.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
        homePresenter.checkFavorite()
        showHistory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.MOVIE_LIST_REQUEST_CODE) {
            homePresenter.checkFavorite()
        }
    }

    override fun getTextFromSearchBox(): String = et_search.text.toString()

    override fun getHistoryList(): List<String> = sharedPreferenceHelper.historyList

    override fun showHistory() {
        (rvHistory.adapter as HistoryAdapter).values = getHistoryList()
    }

    override fun showClearTextIcon(isShow: Boolean) {
        if (isShow) {
            ivClearText.visibility = View.VISIBLE
        } else {
            ivClearText.visibility = View.GONE
        }
    }

    override fun showFavoriteColor(color: Int) {
        tvFavorite.setTextColor(ContextCompat.getColor(this, color))
    }

    override fun navigateToMovieListPage(movieName: String, isFavorite: Boolean) {
        startActivityForResult(Intent(this, MovieListActivity::class.java).apply {
            putExtra(Constant.FIELD_MOVIE_NAME, movieName)
            putExtra(Constant.FIELD_FAVORITE, isFavorite)
        }, Constant.MOVIE_LIST_REQUEST_CODE)
    }

    override fun updateHistory(historyList: List<String>) {
        sharedPreferenceHelper.historyList = historyList
    }
}
