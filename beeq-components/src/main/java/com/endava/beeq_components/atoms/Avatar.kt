package com.endava.beeq_components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun Avatar(
    imageUrl: String,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape)
            .background(Color.Gray) // fallback background
    )
}