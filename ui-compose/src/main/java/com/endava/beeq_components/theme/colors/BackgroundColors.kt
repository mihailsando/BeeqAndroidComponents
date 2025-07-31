package endava.beeq.compose.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import endava.beeq.compose.R

object BackgroundColors {

    val brand: Color
        @Composable get() = colorResource(id = R.color.brand)

    val primary: Color
        @Composable get() = colorResource(id = R.color.white)

    val secondary: Color
        @Composable get() = colorResource(id = R.color.neutral_50)

    val tertiary: Color
        @Composable get() = colorResource(id = R.color.neutral_200)

    val inverse: Color
        @Composable get() = colorResource(id = R.color.neutral_900)

    val alt: Color
        @Composable get() = colorResource(id = R.color.neutral_300)


}