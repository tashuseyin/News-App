package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowNews(
    val urlToImage: String,
    val title: String,
    val description: String,
    val url: String
) : Parcelable
