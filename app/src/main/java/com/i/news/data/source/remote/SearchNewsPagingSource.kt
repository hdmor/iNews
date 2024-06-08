package com.i.news.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.i.news.data.source.remote.dto.toNews
import com.i.news.domain.model.Article

class SearchNewsPagingSource(private val apiService: ApiService, private val query: String, private val sources: String) :
    PagingSource<Int, Article>() {

    private var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val news = apiService.searchNews(query = query, page = page, sources = sources).toNews()
            totalNewsCount += news.articles.size
            val articles = news.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == news.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(throwable = exception)
        }
    }
}