package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)