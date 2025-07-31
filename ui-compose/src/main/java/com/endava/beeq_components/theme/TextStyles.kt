package com.endava.beeq_components.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object TextStyles {
    val CheckBoxText = TextStyle(
        fontFamily = FontFamily.Default, // add font with  FontFamily(Font(resId = R.font.family))
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontFeatureSettings = "lnum, tnum"
    )

    val AccordionText = TextStyle(
        fontFamily = FontFamily.Default, // add font with  FontFamily(Font(resId = R.font.family))
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontFeatureSettings = "lnum, tnum"
    )
}
