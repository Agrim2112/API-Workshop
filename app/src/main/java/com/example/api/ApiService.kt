package com.example.api
import com.example.model.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v4/top-headlines?category=general&apikey=7607b60e77c0405aff2a34b22a0be058")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("lang") lang: String,
        @Query("category") category: String,
        @Query("max") max: Int
    ): Response<NewsResult>
}