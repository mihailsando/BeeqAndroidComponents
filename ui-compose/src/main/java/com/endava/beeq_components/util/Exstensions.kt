package endava.beeq.compose.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import endava.beeq.compose.theme.Colors

@Composable
fun dpToSp(dp: Dp): TextUnit {
    val density = LocalDensity.current
    return with(density) { dp.toSp() }
}

fun Color.withEnable(enable: Boolean): Color =
    this.copy(
        alpha = if (enable) 1F
        else Colors.disabled
    )

fun Modifier.withEnable(enable: Boolean): Modifier =
    this.alpha(
        if (enable) 1F
        else Colors.disabled
    )