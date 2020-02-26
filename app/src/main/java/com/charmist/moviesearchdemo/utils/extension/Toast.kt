package com.charmist.moviesearchdemo.utils.extension
import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, length).show()
}