package com.endava.ui_designview.models.accordion

import com.endava.design_tokens.R as DS
import android.content.res.Resources
import com.endava.ui_designview.util.toDp

enum class AccordionSize {
    SMALL, MEDIUM;
    fun getHorizontalPadding(resources: Resources) = when(this) {
        SMALL -> resources.getDimension(DS.dimen.S).toInt()
        MEDIUM -> resources.getDimension(DS.dimen.M).toInt()
    }

    fun getVerticalPadding(resources: Resources) = when(this) {
        SMALL -> resources.getDimension(DS.dimen.XS).toInt()
        MEDIUM -> resources.getDimension(DS.dimen.S).toInt()
    }

    fun getGapPadding(resources: Resources) = when(this) {
        SMALL -> resources.getDimension(DS.dimen.XS).toInt()
        MEDIUM -> resources.getDimension(DS.dimen.S).toInt()
    }

    fun getRadius(resources: Resources) = when(this) {
        SMALL -> resources.getDimension(DS.dimen.accordion_small_radius).toInt()
        MEDIUM -> resources.getDimension(DS.dimen.accordion_medium_radius).toInt()
    }


    companion object {
        fun getSize(size: Int): AccordionSize =
            when (size) {
                0 -> MEDIUM
                1 -> SMALL
                else -> MEDIUM
            }

    }
}