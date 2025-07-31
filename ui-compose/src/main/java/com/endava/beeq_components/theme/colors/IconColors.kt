package endava.beeq.compose.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import endava.beeq.compose.R

object IconColors {
    val brand: Color
        @Composable get() = colorResource(id = R.color.brand)

    val primary: Color
        @Composable get() = colorResource(id = R.color.neutral_900)

    val secondary: Color
        @Composable get() = colorResource(id = R.color.neutral_600)

    val inverse: Color
        @Composable get() = colorResource(id = R.color.neutral_50)

    val alt: Color
        @Composable get() = colorResource(id = R.color.white)

    val success: Color
        @Composable get() = colorResource(id = R.color.success)

    val warning: Color
        @Composable get() = colorResource(id = R.color.warning)

    val danger: Color
        @Composable get() = colorResource(id = R.color.danger)

    val info: Color
        @Composable get() = colorResource(id = R.color.info)
}