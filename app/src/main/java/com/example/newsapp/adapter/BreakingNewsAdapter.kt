 package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsAdapter(private val onItemClickListener: (Int, Boolean) -> Unit) :
    ListAdapter<Article, BreakingNewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            BreakingRowitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    override fun onBindViewHolder(
        holder: BreakingNewsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindChangedFavorite(getItem(position),onItemClickListener,getItem(position).isFavorites)
            }
        }
    }
}

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
