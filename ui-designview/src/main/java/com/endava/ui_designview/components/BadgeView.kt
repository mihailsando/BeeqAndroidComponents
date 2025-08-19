package com.endava.ui_designview.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import coil.size.Dimension
import com.endava.ui_designview.R
import com.endava.ui_designview.models.avatar.BadgeSize
import com.endava.ui_designview.theme.designColors
import com.endava.ui_designview.util.applyBackGroundWithRadius
import com.endava.ui_designview.util.getIntDimen
import com.endava.design_tokens.R as DS

class BadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    private var badgeColor: Int = context.designColors.ui.danger
    private var badgeSize: Int = 2 // M by default
    private var badgeText: String = ""

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BadgeView,
            0, 0
        ).apply {
            try {
                badgeColor =
                    getColor(R.styleable.BadgeView_badgeColor, context.designColors.ui.danger)
                badgeSize = getInt(R.styleable.BadgeView_badgeSize, 2)
                badgeText = getString(R.styleable.BadgeView_badgeText) ?: ""
            } finally {
                recycle()
            }
        }

        setText(badgeText)
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        setSize(badgeSize.toBadgeSize())

        applyBackground()
    }

    fun setSize(size: BadgeSize) {
        val height = when (size) {
            BadgeSize.Small -> DS.dimen.S.getIntDimen(resources)
            BadgeSize.Medium-> DS.dimen.M.getIntDimen(resources)
            BadgeSize.Large-> DS.dimen.L.getIntDimen(resources)
        }

        val verticalPadding = if (text.length < 3) Dimension(1).px else Dimension(4).px
        val horizontalPadding = if (text.isNullOrBlank()) {
            height / 2
        } else
            Dimension(12).px

        setTextSize(TypedValue.COMPLEX_UNIT_SP, (height / 5).toFloat());

        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

        this.height = height

    }

    private fun applyBackground() {
        applyBackGroundWithRadius(
            color = badgeColor,
            topLeft = (height + paddingLeft) / 2,
            topRight = (height + paddingRight) / 2,
            bottomLeft = (height + paddingLeft) / 2,
            bottomRight = (height + paddingRight) / 2
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Re-set radius once we know the real height
        applyBackground()
    }

    private fun Int.toBadgeSize(): BadgeSize = when(this) {
        1 -> BadgeSize.Small
        2 -> BadgeSize.Medium
        3 -> BadgeSize.Large
        else -> BadgeSize.Medium
    }
}