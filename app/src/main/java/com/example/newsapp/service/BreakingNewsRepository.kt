package com.example.newsapp.service

import com.example.newsapp.model.Article


class BreakingNewsRepository {

    private val breakingNewsDao = BreakingNewsDatabase.getDatabase()?.breakingNewsDao()

    suspend fun insert(article: Article){
        breakingNewsDao?.insertBreakingNews(article)
    }
}