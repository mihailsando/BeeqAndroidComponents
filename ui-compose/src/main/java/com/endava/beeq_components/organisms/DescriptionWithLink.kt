package com.endava.beeq_components.organisms

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun DescriptionWithLink(
    description: String = "Description ",
    linkText: String = "Link",
    onLinkClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        append(description)
        pushStringAnnotation(tag = "URL", annotation = "internal_link") // tag arbitrar
        withStyle(style = SpanStyle(color = Color(0xFF1A73E8), fontWeight = FontWeight.Medium)) {
            append(linkText)
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        onClick = { offset ->
            annotatedText.getStringAnnotations("URL", start = offset, end = offset)
                .firstOrNull()?.let {
                    onLinkClick()
                }
        }
    )
}