package com.example.newsapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://newsapi.org/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .connectTimeout(1000,TimeUnit.MILLISECONDS)
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Service by lazy {
        retrofit.create(Service::class.java)
    }


}