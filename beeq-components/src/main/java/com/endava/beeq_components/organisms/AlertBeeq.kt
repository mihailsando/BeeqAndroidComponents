package com.endava.beeq_components.organisms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.R
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.Radius
import kotlinx.coroutines.delay

sealed interface AlertType {
    @get:DrawableRes
    val icon: Int

    @get:Composable
    val iconTint: Color

    @get:Composable
    val background: Color

    @get:Composable
    val borderColor: Color

    data object Info : AlertType {
        override val icon: Int
            get() = R.drawable.info
        override val iconTint: Color
            @Composable
            get() = BrandColors.brand
        override val background: Color
            @Composable
            get() = BrandColors.brand_light
        override val borderColor: Color
            @Composable
            get() = BrandColors.brand
    }

    data object Success : AlertType {
        override val icon: Int
            get() = R.drawable.success
        override val iconTint: Color
            @Composable
            get() = BrandColors.success
        override val background: Color
            @Composable
            get() = BrandColors.success_light
        override val borderColor: Color
            @Composable
            get() = BrandColors.success
    }

    data object Warning : AlertType {
        override val icon: Int
            get() = R.drawable.warning
        override val iconTint: Color
            @Composable
            get() = BrandColors.warning
        override val background: Color
            @Composable
            get() = BrandColors.warning_light
        override val borderColor: Color
            @Composable
            get() = BrandColors.warning
    }

    data object Error : AlertType {
        override val icon: Int
            get() = R.drawable.error
        override val iconTint: Color
            @Composable
            get() = BrandColors.danger
        override val background: Color
            @Composable
            get() = BrandColors.danger_light
        override val borderColor: Color
            @Composable
            get() = BrandColors.danger
    }

    data object Message : AlertType {
        override val icon: Int
            get() = R.drawable.star
        override val iconTint: Color
            @Composable
            get() = BrandColors.neutral_600
        override val background: Color
            @Composable
            get() = BrandColors.white
        override val borderColor: Color
            @Composable
            get() = BrandColors.neutral_600
    }
}

@Composable
fun AlertBeeq(
    alertType: AlertType,
    title: String,
    onClose: (() -> Unit),
    autoDismissDuration: Long? = null,
    hideCloseIcon: Boolean = false,
    hideIcon: Boolean = false,
    borderRadius: Dp = Radius.alert_border_radius,
    description: String? = null,
    descriptionComposable: @Composable () -> Unit = {},
    actions: List<@Composable () -> Unit> = emptyList()
) {
    LaunchedEffect(autoDismissDuration) {
        if (autoDismissDuration != null) {
            delay(autoDismissDuration)
            onClose()
        }
    }

    Surface(
        shape = RoundedCornerShape(borderRadius),
        border = BorderStroke(1.dp, alertType.borderColor),
        color = alertType.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (hideIcon == false) {
                    Icon(
                        painter = painterResource(alertType.icon),
                        contentDescription = "Info",
                        tint = alertType.iconTint
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
                    description?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it, color = Color.DarkGray)
                    }
                    descriptionComposable()
                }

                if (hideCloseIcon == false) {
                    IconButton(
                        modifier = Modifier.size(16.dp),
                        onClick = onClose
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            }

            if (actions.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    actions.forEach { it() }
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
private fun AlertInfoPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        AlertBeeq(
            alertType = AlertType.Info,
            title = "test message",
            onClose = {

            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Success,
            title = "test message",
            hideIcon = true,
            onClose = {}
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Warning,
            title = "test message",
            hideCloseIcon = true,
            onClose = {},
            description = "description"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Error,
            onClose = {},
            title = "test message",
            description = "description, description, description",
            descriptionComposable = {
                DescriptionWithLink(
                    description = "For more details, check the ",
                    linkText = "documentation",
                    onLinkClick = { }
                )
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Message,
            title = "test message",
            onClose = {},
            hideCloseIcon = true,
            description = "description, description, description",
            descriptionComposable = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A73E8), // Blue
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "button 1")
                    }

                    TextButton(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color(0xFF1A73E8) // Blue
                        )
                    ) {
                        Text(text = "button 2")
                    }
                }
            }
        )
    }
}