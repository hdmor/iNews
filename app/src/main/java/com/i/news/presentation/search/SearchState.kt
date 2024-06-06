package com.i.news.presentation.search

import androidx.paging.PagingData
import com.i.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val query: String = "",
    val articles: Flow<PagingData<Article>>? = null,

)
