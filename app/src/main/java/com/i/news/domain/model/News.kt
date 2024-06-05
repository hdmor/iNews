package com.i.news.domain.model


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)