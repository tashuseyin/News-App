package com.example.newsapp.data

import androidx.paging.PagingSource
import com.example.newsapp.api.Request
import com.example.newsapp.model.Article

class NewsDataSource:  PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key?:1
        val pageSize = params.loadSize?: 1
        val prevKey = if (page == 1) null else page - 1

        val response = Request.getArticleNews2(page, pageSize)
        return LoadResult.Page(
            data = response.articles,
            prevKey = prevKey,
            nextKey = page.plus(1)
        )
    }

}