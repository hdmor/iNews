package com.i.news.presentation

sealed class Routes(val route: String) {
    data object OnBoardingScreen : Routes("onboarding")
    data object HomeScreen : Routes("home")
    data object SearchScreen : Routes("search")
    data object BookmarkScreen : Routes("bookmarks")
    data object DetailScreen : Routes("detail")
    data object AppStartNavigation : Routes("start_navigation")
    data object NewsNavigation : Routes("news_navigation")
    data object NewsNavigatorScreen : Routes("news")
}