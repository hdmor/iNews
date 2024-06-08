package com.i.news.presentation.navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.news.R
import com.i.news.ui.theme.INewsTheme

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String
)

@Composable
fun NewsBottomNavigation(modifier: Modifier = Modifier, items: List<BottomNavigationItem>, selected: Int, onItemClicked: (Int) -> Unit) {
    NavigationBar(modifier = modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.background, tonalElevation = 10.dp) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onItemClicked(index) },
                icon = { Icon(imageVector = bottomNavigationItem.icon, contentDescription = bottomNavigationItem.text) },
                label = { Text(text = bottomNavigationItem.text, style = MaterialTheme.typography.labelSmall) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NewsBottomNavigationPreview() {
    INewsTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.BottomCenter) {
            NewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(Icons.Default.Home, "Home"),
                    BottomNavigationItem(Icons.Default.Search, "Search"),
                    BottomNavigationItem(Icons.Default.BookmarkBorder, "Bookmark")
                ),
                selected = 0,
                onItemClicked = { /* todo */ }
            )
        }
    }
}