package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Article
import com.example.newsapp.service.BreakingNewsRepository

class FavoritesViewModel : ViewModel() {

    private val repository = BreakingNewsRepository

    private var _favorites: LiveData<List<Article>>? = favoriteBreaking()
    val favorites = _favorites

    private fun favoriteBreaking() = repository.favoriteArticle()


    suspend fun updateNews(article: Article) {
        repository.updateNews(article)
    }
}