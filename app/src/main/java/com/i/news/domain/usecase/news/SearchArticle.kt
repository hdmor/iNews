package com.i.news.domain.usecase.news

import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository

class SearchArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Article? = newsRepository.searchArticle(url)
}