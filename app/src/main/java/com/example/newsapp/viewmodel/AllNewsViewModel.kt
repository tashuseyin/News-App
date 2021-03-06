package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsapp.api.Request
import com.example.newsapp.model.Article
import com.example.newsapp.service.BreakingNewsRepository
import kotlinx.coroutines.launch

class AllNewsViewModel : ViewModel() {

    private val repository = BreakingNewsRepository


    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRequest = false

    val listData = Pager(PagingConfig(pageSize = 5, enablePlaceholders = false)) {
        repository.getAllNews()!!
    }.flow.cachedIn(viewModelScope)


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
}
