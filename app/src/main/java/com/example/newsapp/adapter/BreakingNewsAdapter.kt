package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<BreakingNewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            BreakingRowitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size


}