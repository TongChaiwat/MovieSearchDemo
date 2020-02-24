package com.charmist.moviesearchdemo

import android.app.Application
import com.charmist.moviesearchdemo.model.Database

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Database.init(applicationContext)
    }
}