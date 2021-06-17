package com.example.newsapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)