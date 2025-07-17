package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.organisms.datePicker.BeeqDatePickerField
import com.endava.beeq_components.organisms.datePicker.DatePickerMode
import java.util.Calendar

@Composable
fun DatePickerScreen() {
    val min = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -10) } }
    val max = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 30) } }

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {

        SectionHeader("Date Picker")

        BeeqDatePickerField(
            label = "Single",
            key = "preview_single",
            mode = DatePickerMode.Single(),
            minDate = min,
            maxDate = max
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