package com.i.news.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.news.R
import com.i.news.domain.model.Page
import com.i.news.domain.model.pages
import com.i.news.ui.theme.INewsTheme

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier, page: Page) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = 30.dp),
            color = colorResource(id = R.color.display_small),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = 30.dp),
            color = colorResource(id = R.color.text_medium),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingPagePreview() {
    INewsTheme {
        OnBoardingPage(page = pages[0])
    }
}