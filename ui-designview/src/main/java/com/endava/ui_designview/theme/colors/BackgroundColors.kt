package com.endava.ui_designview.theme.colors

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.endava.design_tokens.R as DS

class BackgroundColorAccessor(private val context: Context) {
    val brand get() = ContextCompat.getColor(context, DS.color.brand)
    val primary get() = ContextCompat.getColor(context, DS.color.white)
    val secondary get() = ContextCompat.getColor(context, DS.color.neutral_50)
    val tertiary get() = ContextCompat.getColor(context, DS.color.neutral_200)
    val inverse get() = ContextCompat.getColor(context, DS.color.neutral_900)
    val alt get() = ContextCompat.getColor(context, DS.color.neutral_300)
}