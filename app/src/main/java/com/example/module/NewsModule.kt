package com.example.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsModule {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://gnews.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}