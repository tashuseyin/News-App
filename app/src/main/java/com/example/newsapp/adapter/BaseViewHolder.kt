package com.example.newsapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.model.Article


class BaseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent) {

    fun bind(article: Article, onItemClickListener: (Int) -> Unit) {
    }

}