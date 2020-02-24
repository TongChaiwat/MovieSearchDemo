package com.charmist.moviesearchdemo.utils.extension

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <T> Call<T>.enqueue(
    crossinline successHandler: (T?) -> Unit,
    crossinline failureHandler: (Any) -> Unit
) =
    enqueue(object : Callback<T> {

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            response?.let {
                if (it.isSuccessful) {
                    successHandler(it.body())
                } else {
                    failureHandler(Any())
                }
            } ?: run {
                failureHandler(Any())
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            failureHandler(Any())
        }
    })