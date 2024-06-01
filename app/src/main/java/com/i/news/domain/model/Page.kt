package com.i.news.domain.model

import androidx.annotation.DrawableRes
import com.i.news.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum #1",
        description = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum #2",
        description = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem ipsum #3",
        description = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.",
        image = R.drawable.onboarding3
    )
)
