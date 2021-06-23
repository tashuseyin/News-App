package com.example.newsapp.api


import com.example.newsapp.model.NewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET(
        "v2/top-headlines?sources=techcrunch&apiKey=c3004f7614534b179851e4b95bd08ccd"
    )
    fun getBreakingNews(): Call<NewResponse>

    @GET(
        "v2/top-headlines?sources=techcrunch&apiKey=c3004f7614534b179851e4b95bd08ccd"
    )
    suspend fun getBreakingNews2(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): NewResponse

}