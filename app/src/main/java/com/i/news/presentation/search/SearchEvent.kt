package com.i.news.presentation.search

sealed class SearchEvent {
    data class SearchQueryChanged(val query: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}