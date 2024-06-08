package com.i.news.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.i.news.R
import com.i.news.domain.model.Article
import com.i.news.presentation.common.ArticlesList

@Composable
fun BookmarkScreen(modifier: Modifier = Modifier, state: BookmarkState, navigateToDetail: (Article) -> Unit) {
    Column(modifier = modifier.fillMaxSize().statusBarsPadding().padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
        Text(
            text = "Bookmark",
            color = colorResource(id = R.color.text_title),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ArticlesList(articles = state.articles, onClick = { navigateToDetail(it) })
    }
}