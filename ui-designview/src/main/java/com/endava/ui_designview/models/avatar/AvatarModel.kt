package com.endava.ui_designview.models.avatar

import android.content.res.Resources
import com.endava.ui_designview.util.getIntDimen
import com.endava.design_tokens.R as DS

data class AvatarModel(
    private val sizeAttribute: Int,
    private val radiusAttribute: Int,
    private val height: Int
) {

    fun getSize(resources: Resources): Int = when (sizeAttribute) {
        // XS
        0 -> DS.dimen.L.getIntDimen(resources)
        // S
        1 -> DS.dimen.XL.getIntDimen(resources)
        // L
        3 -> DS.dimen.XXL3.getIntDimen(resources)
        // M
        else -> DS.dimen.XXL.getIntDimen(resources)
    }

    fun getRadius(resources: Resources): Int {
        // CIRCLE
        return if (radiusAttribute == 0) {
            getSize(resources) / 2
        }
        // SQUARE
        else when (sizeAttribute) {
            // XS
            0 -> DS.dimen.avatar_border_radius_square_XS.getIntDimen(resources)
            // S
            1 -> DS.dimen.avatar_border_radius_square_S.getIntDimen(resources)
            // L
            3 -> DS.dimen.avatar_border_radius_square_L.getIntDimen(resources)
            // M
            else -> DS.dimen.avatar_border_radius_square_M.getIntDimen(resources)
        }
    }

    val textSize: Float
        get() = when (sizeAttribute) {
            // XS
            0 -> 8f
            // S
            1 -> 12f
            // L
            3 -> 20f
            // M
            else -> 20f
        }

    val badgeMarginTop: Int = when (sizeAttribute) {
        // XS
        0 -> 0
        // S
        1 -> 3
        // L
        3 -> 20
        // M
        else -> 4
    }

}