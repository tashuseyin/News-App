package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.Request
import com.example.newsapp.model.Article
import com.example.newsapp.service.BreakingNewsRepository
import kotlinx.coroutines.launch

class BreakingNewsViewModel : ViewModel() {

    private val repository = BreakingNewsRepository

    var _news: LiveData<List<Article>>? = getArticle()
    val news = _news

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRequested = false
    private var currentData: List<Article>? = null


    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        isLoading.value = true
        Request.getArticleNews { articleList ->
            isLoading.value = false
            viewModelScope.launch {
                articleList?.forEach {
                    insert(it)
                }
            }
        }
    }


    suspend fun insert(article: Article) {
        repository.insert(article)
    }

    private fun getArticle(): LiveData<List<Article>>? = repository.getAllArticle()

    suspend fun replaceArticle(article: Article) {
        repository.replaceArticle(article)
    }



    suspend fun updateNews(article: Article) {
        repository.updateNews(article)
    }

}


