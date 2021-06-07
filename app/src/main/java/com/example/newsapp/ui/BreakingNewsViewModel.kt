package com.example.newsapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.RetrofitClient
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreakingNewsViewModel : ViewModel() {

    val news: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    var isRequested = false

    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        isLoading.value = true
        RetrofitClient.api.getBreakingNews().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                isLoading.value = false
                news.value = response.body()?.articles
            }

            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                isLoading.value = false
                Log.d("TAG", "Veriyi Alamadım")
            }
        })
    }

}