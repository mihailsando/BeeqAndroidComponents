package com.endava.beeq_components.organisms.datePicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

class BeeqCalendarViewModel(
    val mode: DatePickerMode,
    val minDate: Calendar? = null,
    val maxDate: Calendar? = null
) : ViewModel() {

    private val _isMonthSelectorOpen = MutableStateFlow(false)
    val isMonthSelectorOpen: StateFlow<Boolean> = _isMonthSelectorOpen

    private val _selectedDates = MutableStateFlow<Set<Calendar>>(emptySet())
    val selectedDates: StateFlow<Set<Calendar>> = _selectedDates.asStateFlow()

    private val _rangeStart = MutableStateFlow<Calendar?>(null)
    val rangeStart: StateFlow<Calendar?> = _rangeStart

    private val _rangeEnd = MutableStateFlow<Calendar?>(null)
    val rangeEnd: StateFlow<Calendar?> = _rangeEnd

    var currentMonth = MutableStateFlow(Calendar.getInstance())

    init {
        when (mode) {
            is DatePickerMode.Single -> mode.selectedDate?.let { _selectedDates.value = setOf(it) }
            is DatePickerMode.Multiple -> mode.selectedDates.takeIf { it.isNotEmpty() }?.let {
                _selectedDates.value = it.toSet()
            }

            is DatePickerMode.Range -> {
                mode.startDate?.let {
                    _rangeStart.value = it
                }
                mode.endDate?.let {
                    _rangeEnd.value = it
                }
            }
        }
    }

    fun onDateSelected(date: Calendar) {
        date.applyMidnight()
        if (minDate?.let { date.before(it) } == true) return
        if (maxDate?.let { date.after(it) } == true) return

        when (mode) {
            is DatePickerMode.Single -> _selectedDates.value = setOf(date)
            is DatePickerMode.Multiple -> {
                _selectedDates.update { dates ->
                    if (dates.any { it.isSameDay(date) })
                        dates.filterNot { it.isSameDay(date) }.toSet()
                    else dates + date
                }
            }

            is DatePickerMode.Range -> {
                if (_rangeStart.value == null || _rangeEnd.value != null) {
                    _rangeStart.value = date
                    _rangeEnd.value = null
                    _selectedDates.value = setOf(date)
                } else {
                    _rangeEnd.value = date
                    val range = datesBetween(_rangeStart.value!!, date)
                    _selectedDates.value = range.toSet()
                }
            }
        }
    }


    fun goToPreviousMonth() {
        currentMonth.value = (currentMonth.value.clone() as Calendar).apply {
            add(Calendar.MONTH, -1)
        }
    }

    fun goToNextMonth() {
        currentMonth.value = (currentMonth.value.clone() as Calendar).apply {
            add(Calendar.MONTH, 1)
        }
    }

    fun toggleMonthSelector() {
        _isMonthSelectorOpen.value = !_isMonthSelectorOpen.value
    }

    fun incrementYear() {
        currentMonth.value = (currentMonth.value.clone() as Calendar).apply {
            add(Calendar.YEAR, 1)
        }
    }

    fun decrementYear() {
        currentMonth.value = (currentMonth.value.clone() as Calendar).apply {
            add(Calendar.YEAR, -1)
        }
    }

    fun setMonthTo(date: Calendar) {
        currentMonth.value = (date.clone() as Calendar).apply {
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }

    fun selectMonth(monthIndex: Int) {
        currentMonth.value = (currentMonth.value.clone() as Calendar).apply {
            set(Calendar.MONTH, monthIndex)
        }
        _isMonthSelectorOpen.value = false
    }

    fun resetMonthSelector() {
        _isMonthSelectorOpen.value = false
    }
}

private fun datesBetween(start: Calendar, end: Calendar): List<Calendar> {
    val list = mutableListOf<Calendar>()
    val clone = start.clone() as Calendar
    val step = if (start.before(end)) 1 else -1
    while (true) {
        list.add(clone.clone() as Calendar)
        if (clone.isSameDay(end)) break
        clone.add(Calendar.DAY_OF_MONTH, step)
    }
    return list
}

@Composable
fun rememberBeeqCalendarViewModel(
    key: String,
    mode: DatePickerMode,
    minDate: Calendar? = null,
    maxDate: Calendar? = null
): BeeqCalendarViewModel {
    val factory = remember(mode, minDate, maxDate) {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BeeqCalendarViewModel(mode, minDate, maxDate) as T
            }
        }
    }

    return viewModel(key = key, factory = factory)
}
