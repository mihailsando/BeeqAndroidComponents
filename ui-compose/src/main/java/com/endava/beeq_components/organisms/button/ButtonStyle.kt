package endava.beeq.compose.organisms.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import endava.beeq.compose.theme.Colors


sealed class ButtonStyle(
    val colorStyle: BeeqButtonColors
) {
    @get:Composable
    abstract val colors: ButtonColors

    @get:Composable
    abstract val iconsColor: Color?

    @get:Composable
    abstract val borderStrokeColor: Color?

    data class NormalButton(private val colorsStyle: BeeqButtonColors) :
        ButtonStyle(colorsStyle) {
        override val colors: ButtonColors
            @Composable
            get() = when (colorsStyle) {
                is BeeqButtonColors.Primary -> ButtonDefaults.buttonColors(
                    containerColor = Colors.ui.brand,
                    contentColor = Colors.text.alt,
                    disabledContentColor = Colors.text.alt.copy(alpha = Colors.disabled),
                    disabledContainerColor = Colors.ui.brand.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Secondary -> ButtonDefaults.buttonColors(
                    containerColor = Colors.ui.secondary,
                    contentColor = Colors.text.primary,
                    disabledContainerColor = Colors.ui.secondary.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.primary.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Danger -> ButtonDefaults.buttonColors(
                    containerColor = Colors.ui.danger,
                    contentColor = Colors.text.alt,
                    disabledContainerColor = Colors.ui.danger.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.alt.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Success -> ButtonDefaults.buttonColors(
                    containerColor = Colors.ui.success,
                    contentColor = Colors.text.alt,
                    disabledContainerColor = Colors.ui.brand.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.alt.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Custom -> colorsStyle.customColor
            }
        override val iconsColor: Color?
            @Composable
            get() = when (colorsStyle) {
                is BeeqButtonColors.Primary -> Colors.icon.alt
                is BeeqButtonColors.Secondary -> Colors.icon.primary
                is BeeqButtonColors.Danger -> Colors.icon.alt
                is BeeqButtonColors.Success -> Colors.icon.alt
                is BeeqButtonColors.Custom -> colorsStyle.iconsColor
            }
        override val borderStrokeColor: Color?
            @Composable
            get() = null
    }

    data class GhostButton(private val colorsStyle: BeeqButtonColors) :
        ButtonStyle(colorsStyle) {
        override val colors: ButtonColors
            @Composable
            get() = when (colorsStyle) {
                is BeeqButtonColors.Primary -> ButtonDefaults.outlinedButtonColors(
                    containerColor = Colors.ui.primary,
                    contentColor = Colors.text.brand,
                    disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.brand.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Secondary -> ButtonDefaults.outlinedButtonColors(
                    containerColor = Colors.ui.primary,
                    contentColor = Colors.text.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.primary.copy(alpha = Colors.disabled),
                ).apply {
                    borderStrokeColor
                }

                is BeeqButtonColors.Danger -> ButtonDefaults.outlinedButtonColors(
                    containerColor = Colors.ui.primary,
                    contentColor = Colors.text.danger,
                    disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.danger.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Success -> ButtonDefaults.outlinedButtonColors(
                    containerColor = Colors.ui.primary,
                    contentColor = Colors.text.success,
                    disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = Colors.disabled),
                    disabledContentColor = Colors.text.success.copy(alpha = Colors.disabled),
                )

                is BeeqButtonColors.Custom -> colorsStyle.customColor
            }
        override val iconsColor: Color?
            @Composable
            get() = when (colorsStyle) {
                is BeeqButtonColors.Primary -> Colors.icon.brand
                is BeeqButtonColors.Secondary -> Colors.icon.primary
                is BeeqButtonColors.Danger -> Colors.icon.danger
                is BeeqButtonColors.Success -> Colors.icon.success
                is BeeqButtonColors.Custom -> colorsStyle.iconsColor
            }
        override val borderStrokeColor: Color?
            @Composable
            get() = when (colorsStyle) {
                is BeeqButtonColors.Primary -> Colors.stroke.brand
                is BeeqButtonColors.Secondary -> Colors.stroke.secondary
                is BeeqButtonColors.Danger ->  Colors.stroke.danger
                is BeeqButtonColors.Success ->Colors.stroke.success
                is BeeqButtonColors.Custom -> colorsStyle.iconsColor
            }
    }
}


sealed interface BeeqButtonColors {

    data object Primary : BeeqButtonColors

    data object Secondary : BeeqButtonColors

    data object Danger : BeeqButtonColors

    data object Success : BeeqButtonColors

    data class Custom(
        val customColor: ButtonColors,
        val iconsColor: Color = customColor.contentColor
    ) : BeeqButtonColors
}