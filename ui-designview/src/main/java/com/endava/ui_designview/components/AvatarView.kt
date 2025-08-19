package com.endava.ui_designview.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import coil.transform.RoundedCornersTransformation
import com.endava.ui_designview.R
import com.endava.ui_designview.models.avatar.AvatarModel
import com.endava.ui_designview.models.avatar.BadgeSize
import com.endava.ui_designview.theme.designColors
import com.endava.ui_designview.util.addMargin
import com.endava.ui_designview.util.applyStroke
import com.google.android.material.imageview.ShapeableImageView


class AvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val avatarImage: ShapeableImageView
    private val avatarText: TextView
    private val badgeView: BadgeView
    private val layoutRoot: FrameLayout

    private val model: AvatarModel

    init {
        LayoutInflater.from(context).inflate(R.layout.avatar_layout, this, true)

        avatarImage = findViewById(R.id.avatar_image)
        avatarText = findViewById(R.id.avatar_text)
        badgeView = findViewById(R.id.avatar_badge)
        layoutRoot = findViewById(R.id.avatar_layout)

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.AvatarView, 0, 0)

        try {

            val avatarType = attributes.getInt(R.styleable.AvatarView_avatarType, 0)
            val shapeAttr = attributes.getInt(R.styleable.AvatarView_avatarShape, 0)
            val sizeAttr = attributes.getInt(R.styleable.AvatarView_avatarSize, 2)

            val showDot = attributes.getBoolean(R.styleable.AvatarView_showDot, false)
            val dotText = attributes.getString(R.styleable.AvatarView_dotText) ?: ""
            val dotColor =
                attributes.getColor(R.styleable.AvatarView_dotColor, context.designColors.ui.danger)

            model = AvatarModel(
                sizeAttribute = sizeAttr,
                radiusAttribute = shapeAttr,
                height = height
            )

            if (avatarType == 0) {
                attributes.setSourceImage()
            } else {
                avatarText.setTextColor(context.designColors.text.primary)
                val text = attributes.getString(R.styleable.AvatarView_avatarInitials) ?: "?"
                avatarText.text = text
            }
            avatarText.isVisible = avatarType != 0
            avatarImage.isVisible = avatarType == 0

            setSize()
            setShape()

            badgeView.apply {
                isVisible = showDot
                text = dotText
                setBackgroundColor(dotColor)
                val badgeSize: BadgeSize = when(sizeAttr) {
                    0-> BadgeSize.Small
                    1 -> BadgeSize.Medium
                    else -> BadgeSize.Large
                }
                setSize(badgeSize)
            }

            badgeView.isVisible = showDot
            badgeView.text = dotText
            badgeView.setBackgroundColor(dotColor)

        } finally {
            attributes.recycle()
        }
    }

    private fun TypedArray.setSourceImage() {
        val typedValue = TypedValue()
        if (getValue(R.styleable.AvatarView_avatarSrc, typedValue)) {
            when (typedValue.type) {
                TypedValue.TYPE_STRING -> {
                    val url = getString(R.styleable.AvatarView_avatarSrc)
                    setImageUrl(url)
                }

                TypedValue.TYPE_REFERENCE -> {
                    val drawable = getDrawable(R.styleable.AvatarView_avatarSrc)
                    if (drawable != null) {
                        avatarImage.setImageDrawable(drawable)
                        avatarImage.visibility = VISIBLE
                        avatarText.visibility = GONE
                    } else {
                        showTextFallback()
                    }
                }

                else -> {
                    showTextFallback()
                }
            }
        } else {
            showTextFallback()
        }
    }

    private fun showTextFallback() {
        avatarImage.visibility = GONE
        avatarText.visibility = VISIBLE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Re-set radius once we know the real height
        setShape()

    }

    private fun setShape() {
        val radius = model.getRadius(resources)

        avatarImage.clipToOutline= true
        layoutRoot.applyStroke(
            strokeWidth = 2,
            strokeColor = context.designColors.stroke.tertiary,
            cornerRadius = radius
        )
    }

    fun setImageUrl (url: String?) {
        if (!url.isNullOrEmpty()) {
            avatarImage.load(url) {
                crossfade(true)
                transformations(
                    RoundedCornersTransformation(model.getRadius(resources).toFloat()),
                )
                listener(
                    onSuccess = { _, _ ->
                        avatarImage.visibility = VISIBLE
                        avatarText.visibility = GONE
                    },
                    onError = { _, error ->
                        showTextFallback()
                        Log.d("AVATAR_VIEW loadImg: " , error.throwable.message ?: "NU STIU" )
                    }
                )
            }
        } else {
            showTextFallback()
        }
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val minSize = 64.dp
//        val finalWidth = resolveSize(minSize, widthMeasureSpec)
//        val finalHeight = resolveSize(minSize, heightMeasureSpec)
//        super.onMeasure(
//            MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
//            MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY)
//        )
//    }

    fun setSize() {
        val size = model.getSize(resources)
        avatarText.setTextSize(TypedValue.COMPLEX_UNIT_SP, model.textSize)
        badgeView.addMargin(top = model.badgeMarginTop )
        layoutRoot.layoutParams = LayoutParams(size, size)
    }

    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}