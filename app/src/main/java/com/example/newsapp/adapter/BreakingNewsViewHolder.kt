package com.example.newsapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.BreakingRowitemBinding
import com.example.newsapp.model.Article

class BreakingNewsViewHolder(private val binding: BreakingRowitemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onItemClickListener: (Int, Boolean) -> Unit) {
        Glide.with(binding.imageBreaking).load(article.urlToImage)
            .into(binding.imageBreaking)
        binding.breakingTitle.text = article.title
        binding.breakingDescription.text = article.description

        if (!article.isFavorites) {
            binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        binding.favoriteButton.setOnClickListener {
            onItemClickListener(adapterPosition, true)
        }

        binding.root.setOnClickListener {
            onItemClickListener(adapterPosition, false)
        }

    }

    fun bindChangedFavorite(
        article: Article,
        onItemClickListener: (Int, Boolean) -> Unit,
        isFavorites: Boolean
    ) {
        binding.favoriteButton.isSelected = isFavorites

        if (!article.isFavorites) {
            binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        binding.favoriteButton.setOnClickListener {
            onItemClickListener(adapterPosition, true)
        }
    }


}

