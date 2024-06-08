package com.i.news.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.news.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SaveOrRemoveArticle -> {
                viewModelScope.launch {
                    val article = useCase.searchArticle(event.article.url)
                    if (article == null) {
                        useCase.storeArticle(event.article)
                        sideEffect = "Article stored."
                    } else {
                        useCase.deleteArticle(event.article)
                        sideEffect = "Article removed."
                    }
                }
            }

            DetailEvent.RemovedSideEffect -> sideEffect = null
        }
    }
}