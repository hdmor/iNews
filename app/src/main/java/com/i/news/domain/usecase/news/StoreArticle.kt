package com.i.news.domain.usecase.news

import com.i.news.domain.model.Article
import com.i.news.domain.repository.NewsRepository

class StoreArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(article: Article) = newsRepository.storeArticle(article)
}