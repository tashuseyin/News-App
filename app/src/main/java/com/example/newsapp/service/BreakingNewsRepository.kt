package com.example.newsapp.service

import androidx.lifecycle.LiveData
import com.example.newsapp.model.Article


object BreakingNewsRepository {

    private val breakingNewsDao by lazy {
        BreakingNewsDatabase.getDatabase()?.breakingNewsDao()
    }


    suspend fun insert(article: Article) {
        breakingNewsDao?.insertBreakingNews(article)
    }

    fun getAllArticle(): LiveData<List<Article>>? =
        breakingNewsDao?.getAllBreakingNews()

    suspend fun replaceArticle(article: Article){
        breakingNewsDao?.replaceBreakingNews(article)
    }

    fun favoriteArticle() = breakingNewsDao?.isFavoriteBreakingNews()

    suspend fun updateNews(article: Article) = breakingNewsDao?.updateNews(article)

}