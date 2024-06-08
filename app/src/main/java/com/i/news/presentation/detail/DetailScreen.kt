package com.i.news.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.i.news.R
import com.i.news.domain.model.Article
import com.i.news.presentation.detail.components.DetailTopBar

@Composable
fun DetailScreen(modifier: Modifier = Modifier, article: Article, event: (DetailEvent) -> Unit, navigateUp: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize().statusBarsPadding()) {
        DetailTopBar(
            onBrowserIconClicked = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) context.startActivity(it)
                }
            },
            onShareIconClicked = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) context.startActivity(it)
                }
            },
            onBookmarkIconClicked = { event(DetailEvent.SaveOrRemoveArticle(article)) },
            onBackIconClicked = navigateUp
        )
        LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp)) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = article.title,
                    modifier = Modifier.fillMaxWidth().height(248.dp).clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = article.title, color = colorResource(id = R.color.text_title), style = MaterialTheme.typography.displaySmall)
                Text(text = article.content, color = colorResource(id = R.color.body), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}