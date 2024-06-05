package com.i.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.news.domain.usecase.app_entry.AppEntryUseCase
import com.i.news.presentation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: AppEntryUseCase) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    init {
        useCase.readAppEntry().onEach {
            startDestination = if (it) Routes.NewsNavigation.route else Routes.AppStartNavigation.route
            delay(500)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}