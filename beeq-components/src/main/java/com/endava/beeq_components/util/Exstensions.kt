package com.endava.beeq_components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun dpToSp(dp: Dp): TextUnit {
    val density = LocalDensity.current
    return with(density) { dp.toSp() }
}