package com.example.bsocdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.NewsResult
import com.example.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository): ViewModel() {
    private val fetchTopNews = MutableLiveData<NewsResult>()
    val fetchTopNewsResponse: LiveData<NewsResult>
        get() = fetchTopNews

    init {
        getTopNews()
    }

    private fun getTopNews() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = newsRepository.getTopNews()
                if(response.isSuccessful){
                    response.body()?.let {
                        fetchTopNews.postValue(it)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}