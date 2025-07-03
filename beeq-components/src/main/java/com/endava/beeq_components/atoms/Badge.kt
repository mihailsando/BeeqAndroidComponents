package com.endava.beeq_components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.util.dpToSp

@Composable
fun Badge(
    content: String = "",
    size: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Red,
    textColor: Color = Color.White,
) {
    Text(
        text = content,
        color = textColor,
        fontSize = dpToSp(size),
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(50))
            .padding(horizontal = size/4, vertical = 0.dp),
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium)
    )
}

@Composable
@Preview(showBackground = true)
fun BadgePreview() {
    Badge(
        content = "online",
        size = 10.dp,
        backgroundColor = BrandColors.success,
        modifier = Modifier
    )
}

