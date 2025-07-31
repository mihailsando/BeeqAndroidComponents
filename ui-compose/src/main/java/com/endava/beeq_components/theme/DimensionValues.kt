package com.endava.beeq_components.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.endava.beeq_components.R

object DimensionValues {

    val button_stroke_weight: Dp
        @Composable get() = dimensionResource(id = R.dimen.button_stroke_weight)
}