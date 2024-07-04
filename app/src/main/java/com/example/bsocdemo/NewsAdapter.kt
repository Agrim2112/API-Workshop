package com.example.bsocdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bsocdemo.databinding.ItemNewsBinding
import com.example.model.Articles

class NewsAdapter(private val context: Context, private val NewsList: MutableList<Articles>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        val newsTitle = binding.tvTitle
        val newsImage = binding.ivNews
        val newsContent: TextView = binding.tvContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return NewsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = NewsList[position]

        val trimmedText = if (model.title.length >= 17) {
            model.title.substring(0, 14) + "..."
        } else {
            model.title
        }

        holder?.newsTitle?.text=trimmedText
        holder?.newsContent?.text=model.description
        Glide.with(context)
            .load(model.image)
            .into(holder.newsImage)

    }

}