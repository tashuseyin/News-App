package com.example.newsapp.api

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Request {
    fun getArticleNews(onResponse: (List<Article>?) -> Unit) {
        RetrofitClient.api.getBreakingNews().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                val articleList = response.body()?.articles
                if (articleList != null) {
                    onResponse(articleList)
                }
            }

            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                onResponse(null)
            }
        })

    }


}