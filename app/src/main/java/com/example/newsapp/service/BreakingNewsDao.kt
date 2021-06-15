package com.example.newsapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.model.Article

@Dao
interface BreakingNewsDao {

    @Insert
    suspend fun insertBreakingNews(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getAllBreakingNews() : List<Article>



}