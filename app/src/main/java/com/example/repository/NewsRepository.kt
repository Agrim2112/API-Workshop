package com.example.repository

import com.example.api.ApiService

class NewsRepository(val apiService: ApiService) {
    suspend fun getTopNews() = apiService.getTopNews("in","en","general",20)
}