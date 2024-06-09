package com.i.news.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.news.R
import com.i.news.ui.theme.INewsTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onValueChanged: (String) -> Unit,
    onSearchImeActionButtonClicked: () -> Unit,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) onClick?.invoke()
    }
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth().searchBarBorder(),
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodySmall,
            placeholder = { Text(text = "Search", color = colorResource(id = R.color.placeholder), style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier.size(20.dp),
                    tint = colorResource(id = R.color.body)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchImeActionButtonClicked() }),
            singleLine = true,
            interactionSource = interactionSource,
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.input_background),
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

fun Modifier.searchBarBorder() = composed {
    if (!isSystemInDarkTheme())
        border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.medium)
    else this
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SearchBarPreview() {
    INewsTheme {
        SearchBar(text = "test", readOnly = false, onValueChanged = {}, onSearchImeActionButtonClicked = { /*TODO*/ })
    }
}