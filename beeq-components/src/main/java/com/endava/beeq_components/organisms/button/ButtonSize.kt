package com.endava.beeq_components.organisms.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ButtonSize() {
    abstract val itemSpace: Dp
    abstract val contentPadding: PaddingValues

    data object Small : ButtonSize() {
        override val itemSpace = 8.dp
        override val contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    }

    data object Medium : ButtonSize() {
        override val itemSpace = 8.dp
        override val contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 16.dp
        )
    }

    data object Large : ButtonSize() {
        override val itemSpace = 8.dp
        override val contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp
        )
    }
}