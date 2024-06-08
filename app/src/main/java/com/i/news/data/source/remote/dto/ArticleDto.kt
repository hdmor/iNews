package com.i.news.data.source.remote.dto


import com.google.gson.annotations.SerializedName
import com.i.news.domain.model.Article

data class ArticleDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

fun ArticleDto.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toSource(),
    title = title,
    url = url,
    urlToImage = urlToImage
)