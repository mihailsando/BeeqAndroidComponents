package com.endava.beeq_components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.endava.beeq_components.organisms.AvatarWithBadge

@Composable
fun Badge(
    content: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Red,
    textColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(backgroundColor, CircleShape)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = content,
            color = textColor,
            style = textStyle,
            modifier = modifier
        )
    }
}