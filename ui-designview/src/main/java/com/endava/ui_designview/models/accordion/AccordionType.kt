package com.endava.ui_designview.models.accordion

import android.content.Context
import android.graphics.Color
import com.endava.ui_designview.theme.designColors


enum class AccordionType {
    GHOST, FILLED;

    fun getColor(context: Context) = when(this) {
        FILLED -> context.designColors.ui.secondary
        GHOST -> Color.TRANSPARENT
    }

    companion object {
        fun getType(type: Int) : AccordionType =
            when(type) {
                0 -> FILLED
                1 -> GHOST
                else -> FILLED
            }
    }
}