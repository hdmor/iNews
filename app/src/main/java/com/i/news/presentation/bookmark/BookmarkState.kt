package com.i.news.presentation.bookmark

import com.i.news.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
