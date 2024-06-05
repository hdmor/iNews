package com.i.news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.i.news.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    val news = useCase.getAllNews(sources = listOf("bbc-news", "abc-news", "al-jazeera-english")).cachedIn(viewModelScope)
}