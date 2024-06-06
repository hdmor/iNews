package com.i.news.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.i.news.presentation.Routes
import com.i.news.presentation.common.ArticlesList
import com.i.news.presentation.common.SearchBar

@Composable
fun SearchScreen(modifier: Modifier = Modifier, state: SearchState, event: (SearchEvent) -> Unit, navigate: (String) -> Unit) {

    Column(modifier = modifier.fillMaxSize().padding(start = 8.dp, top = 8.dp, end = 8.dp).statusBarsPadding()) {
        SearchBar(
            text = state.query,
            readOnly = false,
            onValueChanged = { event(SearchEvent.SearchQueryChanged(it)) },
            onSearchImeActionButtonClicked = { event(SearchEvent.SearchNews) })
        Spacer(modifier = Modifier.height(8.dp))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles) {
                navigate(Routes.DetailScreen.route)
            }
        }
    }
}