package endava.beeq.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import endava.beeq.compose.R

object DimensionValues {

    val button_stroke_weight: Dp
        @Composable get() = dimensionResource(id = R.dimen.button_stroke_weight)
}