package endava.beeq.compose.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import endava.beeq.compose.R

object StrokeColors {
    val brand: Color
        @Composable get() = colorResource(id = R.color.brand)

    val primary: Color
        @Composable get() = colorResource(id = R.color.neutral_200)

    val secondary: Color
        @Composable get() = colorResource(id = R.color.neutral_700)

    val tertiary: Color
        @Composable get() = colorResource(id = R.color.neutral_900)

    val inverse: Color
        @Composable get() = colorResource(id = R.color.white)

    val accent: Color
        @Composable get() = colorResource(id = R.color.accent)

    val alt: Color
        @Composable get() = colorResource(id = R.color.neutral_50)

    val success: Color
        @Composable get() = colorResource(id = R.color.success)

    val warning: Color
        @Composable get() = colorResource(id = R.color.warning)

    val danger: Color
        @Composable get() = colorResource(id = R.color.danger)

    val info: Color
        @Composable get() = colorResource(id = R.color.info)
}