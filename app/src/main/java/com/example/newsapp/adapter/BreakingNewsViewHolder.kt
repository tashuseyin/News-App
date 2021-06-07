package com.example.newsapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsViewHolder(private val binding: BreakingRowitemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        Glide.with(binding.imageBreaking).load(article.urlToImage).into(binding.imageBreaking)
        binding.breakingTitle.text = article.title
        binding.breakingDescription.text = article.description
    }
}