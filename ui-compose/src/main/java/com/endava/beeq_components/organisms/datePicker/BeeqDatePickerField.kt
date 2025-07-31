package endava.beeq.compose.organisms.datePicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Calendar

sealed class DatePickerMode {
    data class Single(
        val selectedDate: Calendar? = null
    ) : DatePickerMode()
    data class Multiple (
        val selectedDates: List<Calendar> = emptyList<Calendar>()
    ): DatePickerMode()
    data class Range (
        val startDate: Calendar? = null,
        val endDate: Calendar? = null
    ) : DatePickerMode()
}

@Composable
fun BeeqDatePickerField(
    label: String,
    key: String,
    mode: DatePickerMode,
    minDate: Calendar? = null,
    maxDate: Calendar? = null,
    modifier: Modifier = Modifier
) {
    val viewModel: BeeqCalendarViewModel = viewModel(
        key = key,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BeeqCalendarViewModel(mode, minDate, maxDate) as T
            }
        }
    )
    BeeqCalendarDialog(label = label, viewModel = viewModel, modifier = modifier)
}