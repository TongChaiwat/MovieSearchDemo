package com.charmist.moviesearchdemo.model

import com.charmist.moviesearchdemo.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Apis {

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(Constant.API_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    private val interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(
                chain.request().newBuilder()
                    .addHeader(Constant.API_KEY_REQUEST_HEADER, Constant.API_KEY)
                    .build()
            )
        }
    }

    val api: MovieApi

    init {
        api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieApi::class.java)
    }

}
