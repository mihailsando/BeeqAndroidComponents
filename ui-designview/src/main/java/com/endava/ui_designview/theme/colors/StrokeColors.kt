package com.endava.ui_designview.theme.colors

import android.content.Context
import androidx.core.content.ContextCompat
import com.endava.design_tokens.R as DS


class StrokeColorAccessor(private val context: Context) {
    val brand get() = ContextCompat.getColor(context, DS.color.brand)
    val primary get() = ContextCompat.getColor(context, DS.color.neutral_200)
    val secondary get() = ContextCompat.getColor(context, DS.color.neutral_700)
    val tertiary get() = ContextCompat.getColor(context, DS.color.neutral_900)
    val inverse get() = ContextCompat.getColor(context, DS.color.white)
    val accent get() = ContextCompat.getColor(context, DS.color.accent)
    val alt get() = ContextCompat.getColor(context, DS.color.neutral_50)
    val success get() = ContextCompat.getColor(context, DS.color.success)
    val warning get() = ContextCompat.getColor(context, DS.color.warning)
    val danger get() = ContextCompat.getColor(context, DS.color.danger)
    val info get() = ContextCompat.getColor(context, DS.color.info)
}