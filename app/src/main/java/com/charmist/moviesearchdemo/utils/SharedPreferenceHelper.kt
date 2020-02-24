package com.charmist.moviesearchdemo.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceHelper private constructor(context: Context) {

    private val preference: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    var historyList: List<String>
        get() {
            val json = preference.getString(HISTORY_LIST, null)
            if (json != null) {
                return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
            }
            return arrayListOf()
        }
        set(value) {
            val json = Gson().toJson(value)
            preference.edit().putString(HISTORY_LIST, json).apply()
        }


    companion object {
        private var mInstance: SharedPreferenceHelper? = null
        private const val HISTORY_LIST = "SharedPreferenceHelper.HISTORY_LIST"

        fun getInstance(context: Context): SharedPreferenceHelper {
            if (mInstance == null) {
                mInstance = SharedPreferenceHelper(context)
            }
            return mInstance as SharedPreferenceHelper
        }
    }

}
