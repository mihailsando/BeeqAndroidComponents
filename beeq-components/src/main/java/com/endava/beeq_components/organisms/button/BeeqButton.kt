package com.endava.beeq_components.organisms.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Token
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BeeqButton(
    onClick: () -> Unit = {},
    coroutineAction: (suspend () -> Unit)? = null,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    buttonSize: ButtonSize = ButtonSize.Medium,
    style: BeeqButtonStyle,
    loading: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
) {
    var isLoading by remember { mutableStateOf(loading) }
    val coroutineScope = rememberCoroutineScope()

    val effectiveLoading = loading || isLoading

    Button(
        onClick = {
            onClick()
            coroutineAction?.let { action ->
                coroutineScope.launch {
                    isLoading = true
                    try {
                        action()
                    } finally {
                        isLoading = false
                    }
                }
            }
        },
        enabled = enabled && !effectiveLoading,
        modifier = modifier,
        border = style.border,
        shape = RoundedCornerShape(8.dp),
        contentPadding = buttonSize.contentPadding,
        colors = style.colors
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                if (text.isEmpty())
                    0.dp
                else
                    buttonSize.itemSpace
            )
        ) {
            if (effectiveLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = style.colors.contentColor
                )
            } else {
                startIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium)
            )

            endIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonPreview() {
    Column {
        Row {
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.Preview,
                style = BeeqButtonStyle.Primary
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = true,
                enabled = true,
                startIcon = Icons.Default.Preview,
                style = BeeqButtonStyle.Primary
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Token,
                style = BeeqButtonStyle.Danger
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = true,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Token,
                style = BeeqButtonStyle.Danger
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Token,
                style = BeeqButtonStyle.Secondary
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = true,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Token,
                style = BeeqButtonStyle.Secondary
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                endIcon = Icons.Default.Warning,
                style = BeeqButtonStyle.Ghost
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = true,
                enabled = true,
                endIcon = Icons.Default.Warning,
                style = BeeqButtonStyle.Ghost
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                style = BeeqButtonStyle.Text
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "saasdasd",
                buttonSize = ButtonSize.Small,
                loading = true,
                enabled = true,
                style = BeeqButtonStyle.Text
            )
        }
    }
}