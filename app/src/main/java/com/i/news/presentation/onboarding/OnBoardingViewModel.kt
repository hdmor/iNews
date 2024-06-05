package com.i.news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.news.domain.usecase.app_entry.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val useCase: AppEntryUseCase): ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            OnBoardingEvent.SaveAppEntry -> viewModelScope.launch { useCase.saveAppEntry() }
        }
    }
}