package com.example.newsapp.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.Article

@Dao
interface BreakingNewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreakingNews(article: Article)

    @Query("SELECT * FROM Article")
    fun getAllBreakingNews() : LiveData<List<Article>>


    @Query("SELECT * FROM Article WHERE isFavorites")
    fun  isFavoriteBreakingNews() : LiveData<List<Article>>

    @Update
    suspend fun updateNews(article: Article)

    @Query("SELECT * FROM Article ORDER BY publishedAt DESC LIMIT 10")
    fun isLatestNews(): LiveData<List<Article>>
}