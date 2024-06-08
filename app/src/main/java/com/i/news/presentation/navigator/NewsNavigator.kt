package com.i.news.presentation.navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.i.news.domain.model.Article
import com.i.news.presentation.Routes
import com.i.news.presentation.bookmark.BookmarkScreen
import com.i.news.presentation.bookmark.BookmarkViewModel
import com.i.news.presentation.detail.DetailEvent
import com.i.news.presentation.detail.DetailScreen
import com.i.news.presentation.detail.DetailViewModel
import com.i.news.presentation.home.HomeScreen
import com.i.news.presentation.home.HomeViewModel
import com.i.news.presentation.navigator.components.BottomNavigationItem
import com.i.news.presentation.navigator.components.NewsBottomNavigation
import com.i.news.presentation.search.SearchScreen
import com.i.news.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(modifier: Modifier = Modifier) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(Icons.Default.Home, "Home"),
            BottomNavigationItem(Icons.Default.Search, "Search"),
            BottomNavigationItem(Icons.Default.BookmarkBorder, "Bookmark")
        )
    }
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Routes.HomeScreen.route -> 0
            Routes.SearchScreen.route -> 1
            Routes.BookmarkScreen.route -> 2
            else -> 0
        }
    }
    val bottomNavigationVisibility = remember(key1 = backstackState) {
        backstackState?.destination?.route == Routes.HomeScreen.route || backstackState?.destination?.route == Routes.SearchScreen.route || backstackState?.destination?.route == Routes.BookmarkScreen.route
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (bottomNavigationVisibility)
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClicked = {
                        when (it) {
                            0 -> navigateToTab(navController, Routes.HomeScreen.route)
                            1 -> navigateToTab(navController, Routes.SearchScreen.route)
                            2 -> navigateToTab(navController, Routes.BookmarkScreen.route)
                        }
                    }
                )
        }
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()
        NavHost(navController = navController, startDestination = Routes.HomeScreen.route, modifier = Modifier.padding(bottom = bottomPadding)) {
            composable(route = Routes.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = { navigateToTab(navController, Routes.SearchScreen.route) },
                    navigateToDetail = { article -> navigateToDetailScreen(navController, article) }
                )
            }

            composable(route = Routes.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetail = { article -> navigateToDetailScreen(navController, article) })
            }

            composable(route = Routes.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemovedSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article ->
                    DetailScreen(article = article, event = viewModel::onEvent, navigateUp = { navController.navigateUp() })
                }
            }

            composable(route = Routes.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(state = viewModel.state.value, navigateToDetail = { article -> navigateToDetailScreen(navController, article) })
            }
        }
    }
}

private fun navigateToDetailScreen(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Routes.DetailScreen.route)
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) { saveState = true }
            restoreState = true
            launchSingleTop = true
        }
    }
}