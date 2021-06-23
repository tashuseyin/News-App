package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsPagingAdapter(private val onItemClickListener: (Int, Boolean) -> Unit) :
    PagingDataAdapter<Article, BreakingNewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            BreakingRowitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClickListener) }
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
                getItem(position)?.let {
                    holder.bindChangedFavorite(
                        it,
                        onItemClickListener,
                        getItem(position)?.isFavorites == true
                    )
                }
            }
        }
    }
}



