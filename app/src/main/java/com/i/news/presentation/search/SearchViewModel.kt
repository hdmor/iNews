package com.i.news.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.i.news.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> get() = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.SearchNews -> {
                val articles = useCase.searchNews(state.value.query, listOf("bbc-news", "abc-news", "al-jazeera-english")).cachedIn(viewModelScope)
                _state.value = state.value.copy(articles = articles)
            }

            is SearchEvent.SearchQueryChanged -> _state.value = state.value.copy(query = event.query)
        }
    }
}