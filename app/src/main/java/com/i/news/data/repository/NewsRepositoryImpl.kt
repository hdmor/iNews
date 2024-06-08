package com.i.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.i.news.data.source.local.NewsDao
import com.i.news.data.source.remote.ApiService
import com.i.news.data.source.remote.NewsPagingSource
import com.i.news.data.source.remote.SearchNewsPagingSource
import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val apiService: ApiService, private val dao: NewsDao) : NewsRepository {
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

    override fun getAllArticles(): Flow<List<Article>> = dao.selectAllArticles()
    override suspend fun searchArticle(url: String): Article? = dao.getArticle(url)

    override suspend fun storeArticle(article: Article) = dao.insert(article)
    override suspend fun deleteArticle(article: Article) = dao.delete(article)
}