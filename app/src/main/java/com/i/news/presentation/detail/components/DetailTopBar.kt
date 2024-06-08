package com.i.news.presentation.detail.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.i.news.R
import com.i.news.ui.theme.INewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    onBrowserIconClicked: () -> Unit,
    onShareIconClicked: () -> Unit,
    onBookmarkIconClicked: () -> Unit,
    onBackIconClicked: () -> Unit
) {
    TopAppBar(
        title = { /*TODO*/ },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = onBackIconClicked) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            IconButton(onClick = onBookmarkIconClicked) {
                Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = "bookmark")
            }
            IconButton(onClick = onShareIconClicked) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "share")
            }
            IconButton(onClick = onBrowserIconClicked) {
                Icon(imageVector = Icons.Default.OpenInBrowser, contentDescription = "browser")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        )
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailTopBarPreview() {
    INewsTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            DetailTopBar(
                onBrowserIconClicked = { /*TODO*/ },
                onShareIconClicked = { /*TODO*/ },
                onBookmarkIconClicked = { /*TODO*/ },
                onBackIconClicked = {/*TODO*/ }
            )
        }
    }
}