package com.i.news.presentation.onboarding

sealed class OnBoardingEvent {

    data object SaveAppEntry : OnBoardingEvent()
}