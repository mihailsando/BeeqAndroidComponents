package com.endava.ui_designview.theme.colors

import android.content.Context
import androidx.core.content.ContextCompat
import com.endava.design_tokens.R as DS

class UIColorAccessor(private val context: Context) {
    val white get() = ContextCompat.getColor(context, DS.color.white)
    val black get() = ContextCompat.getColor(context, DS.color.black)

    val brand get() = ContextCompat.getColor(context, DS.color.brand)
    val brand_alt get() = ContextCompat.getColor(context, DS.color.brand_light)
    val brand_dark get() = ContextCompat.getColor(context, DS.color.brand_dark)

    val accent get() = ContextCompat.getColor(context, DS.color.accent)
    val accent_alt get() = ContextCompat.getColor(context, DS.color.accent_alt)
    val accent_dark get() = ContextCompat.getColor(context, DS.color.accent_dark)

    val neutral_50 get() = ContextCompat.getColor(context, DS.color.neutral_50)
    val neutral_100 get() = ContextCompat.getColor(context, DS.color.neutral_100)
    val neutral_200 get() = ContextCompat.getColor(context, DS.color.neutral_200)
    val neutral_300 get() = ContextCompat.getColor(context, DS.color.neutral_300)
    val neutral_600 get() = ContextCompat.getColor(context, DS.color.bq_grey_600)

    val success get() = ContextCompat.getColor(context, DS.color.success)
    val success_light get() = ContextCompat.getColor(context, DS.color.success_light)
    val success_dark get() = ContextCompat.getColor(context, DS.color.success_dark)

    val warning get() = ContextCompat.getColor(context, DS.color.warning)
    val warning_alt get() = ContextCompat.getColor(context, DS.color.warning_light)
    val warning_dark get() = ContextCompat.getColor(context, DS.color.warning_dark)

    val danger get() = ContextCompat.getColor(context, DS.color.danger)
    val danger_alt get() = ContextCompat.getColor(context, DS.color.danger_light)
    val danger_dark get() = ContextCompat.getColor(context, DS.color.danger_dark)

    val info get() = ContextCompat.getColor(context, DS.color.info)
    val info_light get() = ContextCompat.getColor(context, DS.color.info_light)
    val info_dark get() = ContextCompat.getColor(context, DS.color.info_dark)

    val primary get() = ContextCompat.getColor(context, DS.color.white)
    val secondary get() = ContextCompat.getColor(context, DS.color.neutral_100)
    val tertiary get() = ContextCompat.getColor(context, DS.color.neutral_300)
    val inverse get() = ContextCompat.getColor(context, DS.color.neutral_900)
    val alt get() = ContextCompat.getColor(context, DS.color.neutral_50)

    val accordionBackground get() = ContextCompat.getColor(context, DS.color.bq_grey_100)
}