package com.i.news.domain.usecase.news

import androidx.paging.PagingData
import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllNews(private val newsRepository: NewsRepository) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> = newsRepository.all(sources)
}