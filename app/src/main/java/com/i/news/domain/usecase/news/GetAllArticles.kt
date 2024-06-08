package com.i.news.domain.usecase.news

import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllArticles(private val newsRepository: NewsRepository) {

    operator fun invoke(): Flow<List<Article>> = newsRepository.getAllArticles()
}