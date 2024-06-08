package com.i.news.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.i.news.R
import com.i.news.domain.model.Article
import com.i.news.presentation.common.ArticlesList
import com.i.news.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, articles: LazyPagingItems<Article>, navigateToSearch: () -> Unit, navigateToDetail: (Article) -> Unit) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10)
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9)).joinToString(" \uD83D\uDFE5 ") { it.title }
            else ""
        }
    }
    Column(modifier = modifier.fillMaxSize().padding(top = 16.dp).statusBarsPadding()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "logo",
            modifier = Modifier.width(150.dp).height(30.dp).padding(horizontal = 8.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "",
            readOnly = true,
            onValueChanged = { /*TODO*/ },
            onSearchImeActionButtonClicked = { /*TODO*/ },
            onClick = navigateToSearch
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = titles,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp).basicMarquee(),
            color = colorResource(id = R.color.placeholder),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        ArticlesList(modifier = Modifier.padding(horizontal = 8.dp), articles = articles, onClick = { navigateToDetail(it) })
    }
}