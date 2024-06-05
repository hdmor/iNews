package com.i.news.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.i.news.domain.model.News

data class NewsDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

fun NewsDto.toNews() = News(articles = articles.map { it.toArticle() }, status = status, totalResults = totalResults)