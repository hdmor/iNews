package com.i.news.domain.usecase.news

data class NewsUseCase(
    val getAllNews: GetAllNews,
    val searchNews: SearchNews,
    val getAllArticles: GetAllArticles,
    val searchArticle: SearchArticle,
    val storeArticle: StoreArticle,
    val deleteArticle: DeleteArticle
)
