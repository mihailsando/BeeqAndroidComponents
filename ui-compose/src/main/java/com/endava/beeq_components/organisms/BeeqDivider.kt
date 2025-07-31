package endava.beeq.compose.organisms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BeeqDivider(
    title: String? = null,
    orientation: Orientation = Orientation.Horizontal,
    dashed: Boolean = false,
    strokeColor: Color = MaterialTheme.colorScheme.outline,
    strokeDashGap: Dp = 4.dp,
    titleAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    modifier: Modifier = Modifier
) {
    if (orientation == Orientation.Horizontal) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            fun Modifier.lineModifier(weight: Float = 1f) = this
                .then(
                    Modifier
                        .weight(weight)
                        .height(1.dp)
                )

            if (title == null) {
                lineComposable(
                    dashed,
                    strokeColor,
                    strokeDashGap,
                    orientation,
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            } else when (titleAlignment) {
                Alignment.Start -> {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = strokeColor,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    lineComposable(
                        dashed,
                        strokeColor,
                        strokeDashGap,
                        orientation,
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                }

                Alignment.CenterHorizontally -> {
                    lineComposable(
                        dashed,
                        strokeColor,
                        strokeDashGap,
                        orientation,
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = strokeColor,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    lineComposable(
                        dashed,
                        strokeColor,
                        strokeDashGap,
                        orientation,
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                }

                Alignment.End -> {
                    lineComposable(
                        dashed,
                        strokeColor,
                        strokeDashGap,
                        orientation,
                        Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = strokeColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxHeight()
        ) {
            lineComposable(
                dashed,
                strokeColor,
                strokeDashGap,
                orientation,
                Modifier
                    .weight(1f)
                    .width(1.dp)
            )
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = strokeColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            lineComposable(
                dashed,
                strokeColor,
                strokeDashGap,
                orientation,
                Modifier
                    .weight(1f)
                    .width(1.dp)
            )
        }
    }
}

@Composable
private fun lineComposable(
    dashed: Boolean,
    strokeColor: Color,
    strokeDashGap: Dp,
    orientation: Orientation,
    modifier: Modifier
) {
    if (!dashed) {
        if (orientation == Orientation.Horizontal) {
            HorizontalDivider(color = strokeColor, thickness = 1.dp, modifier = modifier)
        } else {
            VerticalDivider(color = strokeColor, thickness = 1.dp, modifier = modifier)
        }
    } else {
        Canvas(modifier = modifier) {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, strokeDashGap.toPx()))
            val strokeWidth = 1.dp.toPx()
            if (orientation == Orientation.Horizontal) {
                drawLine(
                    color = strokeColor,
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2),
                    strokeWidth = strokeWidth,
                    pathEffect = pathEffect
                )
            } else {
                drawLine(
                    color = strokeColor,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = pathEffect
                )
            }
        }
    }
}
