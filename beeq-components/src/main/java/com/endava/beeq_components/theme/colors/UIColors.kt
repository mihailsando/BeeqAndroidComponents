package com.endava.beeq_components.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.endava.beeq_components.R

object UIColors {
    val white: Color
        @Composable get() = colorResource(id = R.color.white)
    val black: Color
        @Composable get() = colorResource(id = R.color.black)

    val brand: Color
        @Composable get() = colorResource(id = R.color.brand)
    val brand_alt: Color
        @Composable get() = colorResource(id = R.color.brand_light)
    val brand_dark: Color
        @Composable get() = colorResource(id = R.color.brand_dark)

    val accent: Color
        @Composable get() = colorResource(id = R.color.accent)
    val accent_alt: Color
        @Composable get() = colorResource(id = R.color.accent_alt)
    val accent_dark: Color
        @Composable get() = colorResource(id = R.color.accent_dark)

    val neutral_50: Color
        @Composable get() = colorResource(id = R.color.neutral_50)
    val neutral_100: Color
        @Composable get() = colorResource(id = R.color.neutral_100)
    val neutral_200: Color
        @Composable get() = colorResource(id = R.color.neutral_200)
    val neutral_300: Color
        @Composable get() = colorResource(id = R.color.neutral_300)
    val neutral_600: Color
        @Composable get() = colorResource(id = R.color.bq_grey_600)

    val success: Color
        @Composable get() = colorResource(id = R.color.success)
    val success_light: Color
        @Composable get() = colorResource(id = R.color.success_light)
    val success_dark: Color
        @Composable get() = colorResource(id = R.color.success_dark)

    val warning: Color
        @Composable get() = colorResource(id = R.color.warning)
    val warning_alt: Color
        @Composable get() = colorResource(id = R.color.warning_light)
    val warning_dark: Color
        @Composable get() = colorResource(id = R.color.warning_dark)

    val danger: Color
        @Composable get() = colorResource(id = R.color.danger)
    val danger_alt: Color
        @Composable get() = colorResource(id = R.color.danger_light)
    val danger_dark: Color
        @Composable get() = colorResource(id = R.color.danger_dark)

    val info: Color
        @Composable get() = colorResource(id = R.color.info)
    val info_light: Color
        @Composable get() = colorResource(id = R.color.info_light)
    val info_dark: Color
        @Composable get() = colorResource(id = R.color.info_dark)

    val primary: Color
        @Composable get() = colorResource(id = R.color.white)
    val secondary: Color
        @Composable get() = colorResource(id = R.color.neutral_100)
    val tertiary: Color
        @Composable get() = colorResource(id = R.color.neutral_300)
    val inverse: Color
        @Composable get() = colorResource(id = R.color.neutral_900)
    val alt: Color
        @Composable get() = colorResource(id = R.color.neutral_50)


    val accordionBackground: Color
        @Composable get() = colorResource(id = R.color.bq_grey_100)
}