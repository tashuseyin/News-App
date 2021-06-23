package com.example.newsapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.model.Article

class DiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
        return if (oldItem.isFavorites != newItem.isFavorites) {
            true
        } else {
            null
        }
    }
}