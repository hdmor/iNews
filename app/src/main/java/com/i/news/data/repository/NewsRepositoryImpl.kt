package com.i.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.i.news.data.remote.ApiService
import com.i.news.data.remote.NewsPagingSource
import com.i.news.data.remote.SearchNewsPagingSource
import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val apiService: ApiService) : NewsRepository {
    override fun all(sources: List<String>): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            NewsPagingSource(apiService = apiService, sources = sources.joinToString(separator = ","))
        }
    ).flow

    override fun search(query: String, sources: List<String>): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchNewsPagingSource(apiService = apiService, sources = sources.joinToString(separator = ","), query = query)
        }
    ).flow

}