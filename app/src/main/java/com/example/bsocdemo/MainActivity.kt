package com.example.bsocdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.ApiService
import com.example.bsocdemo.databinding.ActivityMainBinding
import com.example.model.Articles
import com.example.model.NewsResult
import com.example.module.NewsModule
import com.example.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var newsViewModel: NewsViewModel
    lateinit var news: NewsResult
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsModule= NewsModule()
        val apiService = newsModule.retrofit.create(ApiService::class.java)
        val newsRepository = NewsRepository(apiService)
        newsViewModel = NewsViewModel(newsRepository)

        setObservers()
    }

    private fun setObservers() {
        newsViewModel.fetchTopNewsResponse.observe(this){
            if(it!=null){
                news=it
                Log.d("it",it.toString())
            }
            setUpRV()
        }
    }

    private fun setUpRV() {
        var newsList:MutableList<Articles> = arrayListOf()

        for(i in 0..news.articles?.size!!-1){
            try {
                newsList.add(news.articles[i])
            }catch (e:Exception){
                Log.e("Error",e.toString())
            }

        }
        val newsAdapter=NewsAdapter(this,newsList)

        binding.rvNews?.layoutManager= LinearLayoutManager(this)
        binding.rvNews?.adapter=newsAdapter

    }

}



