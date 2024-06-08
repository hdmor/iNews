package com.i.news.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.i.news.presentation.navigator.NewsNavigator
import com.i.news.presentation.onboarding.OnBoardingScreen
import com.i.news.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(route = Routes.AppStartNavigation.route, startDestination = Routes.OnBoardingScreen.route) {
            composable(route = Routes.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }
        navigation(route = Routes.NewsNavigation.route, startDestination = Routes.NewsNavigatorScreen.route) {
            composable(route = Routes.NewsNavigatorScreen.route) {
                NewsNavigator()
            }
        }
    }
}