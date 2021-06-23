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

    private var _news: LiveData<List<Article>>? = isLatestNews()
    val news = _news

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRequest = false

    fun getBreakingNews() {
        if (isRequest) return else isRequest = true
        dataRequest()
    }

    private fun dataRequest() {
        Request.getArticleNews { articleList ->
            isLoading.value = false
            isRefreshing.value = false
            viewModelScope.launch {
                articleList?.forEach {
                    insert(it)
                }
            }
        }
    }

    fun refreshData() {
        isRefreshing.value = true
        dataRequest()
    }


    suspend fun insert(article: Article) {
        repository.insert(article)
    }

    suspend fun updateNews(article: Article) {
        repository.updateNews(article)
    }

    private fun isLatestNews(): LiveData<List<Article>>? = repository.isLatestNews()

}


