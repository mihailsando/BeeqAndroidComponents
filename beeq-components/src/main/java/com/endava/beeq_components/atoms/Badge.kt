package com.endava.beeq_components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.util.dpToSp

@Composable
fun Badge(
    content: String,
    size: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Red,
    textColor: Color = Color.White,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(backgroundColor, CircleShape)
            .size(size)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = content,
            fontSize = dpToSp(size / 1.5f),
            color = textColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BadgePreview() {
    Badge(
        content = "2",
        size = 10.dp,
        modifier = Modifier
    )
}

