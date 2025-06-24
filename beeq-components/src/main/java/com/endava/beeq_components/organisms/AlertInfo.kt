package com.endava.beeq_components.organisms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.R
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.Radius

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
fun AlertInfo(
    modifier: Modifier = Modifier,
    alertType: AlertType,
    message: String
) {
    Row(
        modifier = modifier
            .background(
                color = alertType.background,
                shape = RoundedCornerShape(Radius.alert_border_radius)
            )
            .border(
                1.dp,
                color = alertType.borderColor,
                shape = RoundedCornerShape(Radius.alert_border_radius)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(alertType.icon),
            contentDescription = "Info",
            tint = alertType.iconTint
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = BrandColors.black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlertInfoPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        AlertInfo(
            alertType = AlertType.Info,
            message = "test message"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertInfo(
            alertType = AlertType.Success,
            message = "test message"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertInfo(
            alertType = AlertType.Warning,
            message = "test message"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertInfo(
            alertType = AlertType.Error,
            message = "test message"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertInfo(
            alertType = AlertType.Message,
            message = "test message"
        )
    }
}