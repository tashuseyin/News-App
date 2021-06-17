package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowNews(
    val title: String,
    val image: String,
    val content: String
):Parcelable
