package com.endava.ui_designview.theme.colors

import androidx.annotation.ColorRes
import com.endava.design_tokens.R as DS
import android.content.Context
import androidx.core.content.ContextCompat


class IconColorAccessor(private val context: Context) {
    val brand get() = ContextCompat.getColor(context, DS.color.brand)
    val primary get() = ContextCompat.getColor(context, DS.color.neutral_900)
    val secondary get() = ContextCompat.getColor(context, DS.color.neutral_600)
    val inverse get() = ContextCompat.getColor(context, DS.color.neutral_50)
    val alt get() = ContextCompat.getColor(context, DS.color.white)
    val success get() = ContextCompat.getColor(context, DS.color.success)
    val warning get() = ContextCompat.getColor(context, DS.color.warning)
    val danger get() = ContextCompat.getColor(context, DS.color.danger)
    val info get() = ContextCompat.getColor(context, DS.color.info)
}
