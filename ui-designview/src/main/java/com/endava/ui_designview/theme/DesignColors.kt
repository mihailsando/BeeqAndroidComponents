package com.endava.ui_designview.theme

import android.content.Context
import com.endava.ui_designview.theme.colors.BackgroundColorAccessor
import com.endava.ui_designview.theme.colors.IconColorAccessor
import com.endava.ui_designview.theme.colors.StrokeColorAccessor
import com.endava.ui_designview.theme.colors.TextColorAccessor
import com.endava.ui_designview.theme.colors.UIColorAccessor

val Context.designColors: DesignColorAccessors
    get() = DesignColorAccessors(this)

class DesignColorAccessors(private val context: Context) {
    val icon get() = IconColorAccessor(context)
    val text get() = TextColorAccessor(context)
    val stroke get() = StrokeColorAccessor(context)
    val ui get() = UIColorAccessor(context)
    val background get() = BackgroundColorAccessor(context)
}