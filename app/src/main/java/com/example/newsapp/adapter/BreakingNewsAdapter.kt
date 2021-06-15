package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsAdapter(private val onItemClickListener: (Int) -> Unit) :
    ListAdapter<Article, BreakingNewsViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            BreakingRowitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

}