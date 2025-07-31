package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import endava.beeq.compose.organisms.datePicker.BeeqDatePickerField
import endava.beeq.compose.organisms.datePicker.DatePickerMode
import java.util.Calendar

@Composable
fun DatePickerScreen() {
    val min = remember { Calendar.getInstance().apply { add(Calendar.YEAR, -2) } }
    val max = remember { Calendar.getInstance().apply { add(Calendar.YEAR, 2) } }

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {

        SectionHeader("Date Picker")

        BeeqDatePickerField(
            label = "Single",
            key = "preview_single",
            mode = DatePickerMode.Single(),
        )
        BeeqDatePickerField(
            label = "Multiple",
            key = "preview_multi",
            mode = DatePickerMode.Multiple(),
            minDate = min,
            maxDate = max
        )
        BeeqDatePickerField(
            label = "Range",
            key = "preview_range",
            mode = DatePickerMode.Range(),
            minDate = min,
            maxDate = max
        )
    }
}