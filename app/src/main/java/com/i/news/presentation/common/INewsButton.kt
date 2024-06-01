package com.i.news.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.news.ui.theme.INewsTheme
import com.i.news.ui.theme.WhiteGray

@Composable
fun INewsButton(modifier: Modifier = Modifier, text: String, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = Color.LightGray
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}

@Composable
fun INewsTextButton(modifier: Modifier = Modifier, text: String, onclick: () -> Unit) {
    TextButton(onClick = onclick) {
        Text(text = text, color = WhiteGray, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}

@Preview
@Composable
private fun INewsButtonPreview() {
    INewsTheme {
        Column {
            INewsButton(text = "Button Preview") {}
            INewsTextButton(text = "Text Button Preview") {}
        }
    }
}