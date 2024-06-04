package com.i.news.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.i.news.domain.model.pages
import com.i.news.presentation.common.INewsButton
import com.i.news.presentation.common.INewsTextButton
import com.i.news.presentation.onboarding.components.OnBoardingPage
import com.i.news.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, event: (OnBoardingEvent) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) { pages.size }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(modifier = Modifier.width(52.dp), pageSize = pages.size, selectedPage = pagerState.currentPage)
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    INewsTextButton(text = buttonState.value[0]) {
                        scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage - 1) }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                INewsButton(text = buttonState.value[1]) {
                    scope.launch {
                        if (pagerState.currentPage >= pages.size - 1) event(OnBoardingEvent.SaveAppEntry)
                        else pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}