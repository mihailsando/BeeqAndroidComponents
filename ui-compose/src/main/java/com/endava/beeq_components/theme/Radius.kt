package com.endava.beeq_components.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.endava.beeq_components.R

object Radius {
    val avatar_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.avatar_border_radius_circle)
    val alert_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.alert_border_radius)
    val badge_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.badge_border_radius)
    val breadcrumb_item_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.breadcrumb_item_border_radius)
    val card_borderRadius: Dp
        @Composable get() = dimensionResource(id = R.dimen.card_borderRadius)
    val checkbox_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.checkbox_border_radius)
    val date_picker_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.date_picker_border_radius)
    val dialog_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.dialog_border_radius)
    val input_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.input_border_radius)
    val notification_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.notification_border_radius)
    val option_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.option_border_radius)
    val panel_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.panel_border_radius)
    val select_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.select_border_radius)
    val slider_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.slider_border_radius)
    val switch_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.switch_radius)
    val tag_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.tag_border_radius)
    val textarea_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.textarea_border_radius)
    val toast_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.toast_border_radius)
    val tooltip_border_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.tooltip_border_radius)
    val upload_radius: Dp
        @Composable get() = dimensionResource(id = R.dimen.upload_radius)
}