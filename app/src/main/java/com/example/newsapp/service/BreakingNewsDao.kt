package com.example.newsapp.service

import androidx.room.Dao
import androidx.room.Insert
import com.example.newsapp.model.Article

@Dao
interface BreakingNewsDao {

    @Insert
    fun insertBreakingNews(article: Article)
}