package com.i.news.domain.repository

import androidx.paging.PagingData
import com.i.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun all(sources: List<String>): Flow<PagingData<Article>>
    fun search(query: String, sources: List<String>): Flow<PagingData<Article>>
}