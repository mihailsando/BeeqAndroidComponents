package com.endava.ui_designview.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.endava.ui_designview.R
import com.endava.ui_designview.models.accordion.AccordionSize
import com.endava.ui_designview.models.accordion.AccordionType
import com.endava.ui_designview.theme.designColors
import com.endava.ui_designview.util.addMargin
import com.endava.ui_designview.util.applyBackGroundWithRadius
import com.endava.ui_designview.util.disableUI
import com.endava.ui_designview.util.enableUI

class AccordionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val accordionView: LinearLayout
    private val titleTextView: TextView
    private val headerLayout: LinearLayout
    private val expandImageView: ImageView
    private val contentContainer: LinearLayout
    private val startImageView: ImageView
    private val settingsImageView: ImageView

    private val _isExpanded = MutableLiveData(false)
    val isExpanded: LiveData<Boolean> get() = _isExpanded
    private val isExpandedObserver = Observer<Boolean> { expanded ->
        contentContainer.isVisible = expanded
        expandImageView.setImageResource(if (expanded) R.drawable.minus else R.drawable.plus)
    }

    private var isEnable: Boolean = true
        set(value) {
            if (value)
                this.enableUI()
            else
                this.disableUI()
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.accordion_layout, this, true)
        accordionView = findViewById(R.id.accordionView)
        titleTextView = findViewById(R.id.accordionTitle)
        contentContainer = findViewById(R.id.accordionContentContainer)
        startImageView = findViewById(R.id.accordionStartIcon)
        settingsImageView = findViewById(R.id.accordionSettings)
        expandImageView = findViewById(R.id.accordionExpandIcon)
        headerLayout = findViewById(R.id.accordionHeaderLayout)

        // Set default state from XML attributes
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.AccordionView, 0, 0)

        try {
            isEnable = attributes.getBoolean(R.styleable.AccordionView_enabled, true)
            _isExpanded.value = attributes.getBoolean(R.styleable.AccordionView_initiallyExpanded, false)
            val title = attributes.getString(R.styleable.AccordionView_title) ?: ""
            val withSettings = attributes.getBoolean(R.styleable.AccordionView_withSettings, false)
            val startIcon = attributes.getDrawable(R.styleable.AccordionView_startIcon)
            val type = AccordionType.getType(attributes.getInt(R.styleable.AccordionView_type, 0))
            val size = AccordionSize.getSize(attributes.getInt(R.styleable.AccordionView_size, 0))

            applySizeAndType(size, type)

            titleTextView.apply {
                text = title
                setTextColor(context.designColors.text.primary)
            }

            startIcon?.let {
                startImageView.isVisible = true
                startImageView.setImageDrawable(it)
                startImageView.setColorFilter(
                    context.designColors.icon.primary
                )
            }
            settingsImageView.isVisible = withSettings
        } finally {
            attributes.recycle()
        }
        headerLayout.setOnClickListener {
            toggleExpand()
        }

        _isExpanded.observeForever(isExpandedObserver)
    }

    fun toggleExpand() {
        _isExpanded.value = !(isExpanded.value ?: false)
    }

    private fun applySizeAndType(size: AccordionSize, type: AccordionType) {

        val horizontal = size.getHorizontalPadding(resources)
        val vertical = size.getVerticalPadding(resources)
        val gap = size.getGapPadding(resources)
        val radius = size.getRadius(resources)
        val headerColor = type.getColor(context)

        headerLayout.setPadding(
            horizontal, vertical, horizontal, vertical
        )

        headerLayout.applyBackGroundWithRadius(
            color = headerColor,
            topRight = radius,
            topLeft = radius
        )

        startImageView.addMargin(end = gap)
        settingsImageView.addMargin(end = gap)
    }


    override fun onFinishInflate() {

        val viewsContent = mutableListOf<View>()

        for (i in 1 until childCount) {
            viewsContent.add(getChildAt(i))
        }

        viewsContent.forEach {
            this.removeView(it)
            contentContainer.addView(it)
        }

        super.onFinishInflate()
    }

    fun setOnSettingsClickListener(onClick: OnClickListener) {
        settingsImageView.setOnClickListener { onClick }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _isExpanded.removeObserver(isExpandedObserver)
    }
}