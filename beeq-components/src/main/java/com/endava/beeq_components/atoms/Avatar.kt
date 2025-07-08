package com.endava.beeq_components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.endava.beeq_components.theme.colors.UIColors
import com.endava.beeq_components.theme.StandardDimensions

@Composable
fun Avatar(
    imageUrl: String?,
    initials: String,
    size: StandardDimensions,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    borderColor: Color = UIColors.black,
    background: Color = UIColors.neutral_50,
    borderWidth: Dp = 1.dp
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    val state = painter.state

    val showFallback = imageUrl.isNullOrBlank() || state is AsyncImagePainter.State.Error

    Box(
        modifier = modifier
            .background(borderColor, shape)
            .padding(borderWidth)
            .clip(shape)
            .background(background)         //
            .then(Modifier.size(size.value)),
        contentAlignment = Alignment.Center
    ) {
        if (showFallback) {
            Text(
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
                color = borderColor,
                text = initials,
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        } else {
            Image(
                painter = painter,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    Avatar(
        imageUrl = "",
        initials = "MS",
        size = StandardDimensions.L
    )
}


