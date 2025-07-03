package com.endava.beeq_components.organisms.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.theme.BrandColors

sealed class BeeqButtonStyle {
    @get:Composable
    abstract val colors: ButtonColors

    @get:Composable
    abstract val border: BorderStroke?


    data object Primary : BeeqButtonStyle() {
        override val colors: ButtonColors
            @Composable
            get() = ButtonDefaults.buttonColors(
                containerColor = BrandColors.brand,
                contentColor = BrandColors.white,
                disabledContainerColor = BrandColors.brand.copy(alpha = 0.3f),
                disabledContentColor = BrandColors.white.copy(alpha = 0.6f)
            )
        override val border: BorderStroke?
            @Composable
            get() = null
    }

    data object Secondary : BeeqButtonStyle() {
        override val colors
            @Composable
            get() = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        override val border
            @Composable
            get() = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    }

    data object Danger : BeeqButtonStyle() {
        override val colors
            @Composable
            get() = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF3B30),
                contentColor = Color.White
            )
        override val border: BorderStroke?
            @Composable
            get() = null
    }

    data object Ghost : BeeqButtonStyle() {
        override val colors
            @Composable
            get() = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface
            )

        override val border
            @Composable
            get() = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    }
}