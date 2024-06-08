package com.i.news.presentation.detail

import com.i.news.domain.model.Article

sealed class DetailEvent {
    data class SaveOrRemoveArticle(val article: Article) : DetailEvent()
    data object RemovedSideEffect : DetailEvent()
}