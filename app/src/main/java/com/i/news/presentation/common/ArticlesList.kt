package com.i.news.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.i.news.domain.model.Article

@Composable
fun ArticlesList(modifier: Modifier = Modifier, articles: List<Article>, onClick: (Article) -> Unit) {
    LazyColumn(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(4.dp)) {
        items(count = articles.size) {
            ArticleCard(article = articles[it]) { onClick(articles[it]) }
        }
    }
}

@Composable
fun ArticlesList(modifier: Modifier = Modifier, articles: LazyPagingItems<Article>, onClick: (Article) -> Unit) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(4.dp)) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article) { onClick(article) }
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier.padding(8.dp))
        }
    }
}