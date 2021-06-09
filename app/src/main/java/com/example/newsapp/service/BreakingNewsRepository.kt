package com.example.newsapp.service

import com.example.newsapp.model.Article


class BreakingNewsRepository(private val dao: BreakingNewsDao ) {

    fun insert(article: Article){
        dao.insertBreakingNews(article)
    }
}