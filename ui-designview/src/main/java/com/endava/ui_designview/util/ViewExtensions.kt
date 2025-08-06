package com.endava.ui_designview.util

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import com.endava.design_tokens.Constants.disabledAlpha


fun Float.toDp(resources: Resources): Int {
    val density = resources.displayMetrics.density
    return (this * density).toInt()
}

fun View.applyBackGroundWithRadius(
    color: Int,
    topLeft: Int = 0,
    topRight: Int = 0,
    bottomRight: Int = 0,
    bottomLeft: Int = 0,
) {
    val drawable = GradientDrawable()
    drawable.setColor(color)

    drawable.cornerRadii = floatArrayOf(
        topLeft.toFloat(), topLeft.toFloat(),
        topRight.toFloat(), topRight.toFloat(),
        bottomRight.toFloat(), bottomRight.toFloat(),
        bottomLeft.toFloat(), bottomLeft.toFloat()
    )

    background = drawable
}

fun View.addMargin(
    start: Int? = null,
    end: Int? = null,
    top: Int? = null,
    bottom: Int? = null,
) {
    val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams

    start?.let {
        layoutParams.leftMargin = it
    }
    end?.let {
        layoutParams.rightMargin = it
    }
    top?.let {
        layoutParams.topMargin = it
    }
    bottom?.let {
        layoutParams.bottomMargin = it
    }
}

fun View.disableUI() {
    val alphaFactor = disabledAlpha

    alpha = alphaFactor
    isEnabled = false
    isClickable = false
    isFocusable = false

    if (this is ViewGroup) {
        for (i in 0 until childCount) {
            getChildAt(i).disableUI()
        }
    }
}

fun View.enableUI() {
    alpha = 1f
    isEnabled = true
    isClickable = true
    isFocusable = true

    if (this is ViewGroup) {
        for (i in 0 until childCount) {
            getChildAt(i).enableUI()
        }
    }
}

//fun View.disableUI() {
//    this.apply {
//        alpha = DS.dimen.disable.toFloat()
//    }
//}
//fun View.enableUI() {
//    this.apply {
//        alpha = 1f
//    }
//}