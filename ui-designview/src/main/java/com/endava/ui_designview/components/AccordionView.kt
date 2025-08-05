package com.endava.ui_designview.components


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.endava.ui_designview.theme.designColors
import endava.beeq.designview.R

class AccordionView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val titleTextView: TextView
    private val headerLayout: LinearLayout
    private val expandImageView: ImageView
    private val contentLayout: LinearLayout
    private val startImageView: ImageView
    private val settingsImageView: ImageView
    private var isExpanded = false

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.accordion_layout, this, true)
        titleTextView = findViewById(R.id.accordionTitle)
        contentLayout = findViewById(R.id.contentLayout)
        startImageView = findViewById(R.id.accordionStartIcon)
        settingsImageView = findViewById(R.id.accordionSettings)
        expandImageView = findViewById(R.id.accordionExpandIcon)
        headerLayout = findViewById(R.id.accordionHeaderLayout)


        // Set default state from XML attributes
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.AccordionView, 0, 0)

        try {
            val title = attributes.getString(R.styleable.AccordionView_title) ?: ""
            titleTextView.apply {
                text = title
                setTextColor(context.designColors.text.primary)
            }

            isExpanded = attributes.getBoolean(R.styleable.AccordionView_initiallyExpanded, false)
            toggleExpand()

            val startIcon = attributes.getDrawable(R.styleable.AccordionView_startIcon)
            startIcon?.let {
                startImageView.isVisible = true
                startImageView.setImageDrawable(it)
                startImageView.setColorFilter(
                    context.designColors.icon.primary
                )
            }
            val type = attributes.getInt(R.styleable.AccordionView_type, 1)

            when (type) {
//                GHOST
                0 -> {
                    headerLayout.setBackgroundColor(context.designColors.ui.secondary)
                }
//                 FILLED
            }

            expandImageView.setImageResource(if (isExpanded) R.drawable.minus else R.drawable.plus)

            val isEnabled = attributes.getBoolean(R.styleable.AccordionView_enabled, true)

            if (!isEnabled) {
                headerLayout.isEnabled = false
            }


        } finally {
            attributes.recycle()
        }


        headerLayout.setOnClickListener {
            toggleExpand()
        }
    }

    private fun toggleExpand() {
        isExpanded = !isExpanded
        if (isExpanded) {
            contentLayout.visibility = VISIBLE
            expandImageView.setImageResource(R.drawable.minus)
        } else {
            contentLayout.visibility = GONE
            expandImageView.setImageResource(R.drawable.plus)
        }
    }

    fun setOnSettingsClickListener(onClick: OnClickListener) {
        settingsImageView.setOnClickListener { onClick }
    }
}