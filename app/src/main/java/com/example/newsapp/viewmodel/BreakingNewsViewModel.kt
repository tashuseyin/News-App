package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.RetrofitClient
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewResponse
import com.example.newsapp.service.BreakingNewsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreakingNewsViewModel : ViewModel() {

    private val repository = BreakingNewsRepository()

    val news: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }


    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRequested = false


    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        isLoading.value = true

        RetrofitClient.api.getBreakingNews().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                isLoading.value = false
                news.value = response.body()?.articles
                response.body()?.articles?.forEach {
                    viewModelScope.launch {
                        insert(it)
                    }
                }
            }
            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                isLoading.value = false
            }
        })
    }


    suspend fun insert(article: Article) {
        repository.insert(article)
    }


}


