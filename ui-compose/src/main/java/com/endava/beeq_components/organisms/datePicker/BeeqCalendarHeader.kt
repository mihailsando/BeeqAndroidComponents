package endava.beeq.compose.organisms.datePicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class CalendarHeaderType {
    MONTHS, YEARS
}

@Composable
fun BeeqCalendarHeader(
    type: CalendarHeaderType,
    viewModel: BeeqCalendarViewModel
) {
    val currentMonth = viewModel.currentMonth.collectAsState()
    val minDate = viewModel.minDate
    val maxDate = viewModel.maxDate

    fun getEndOfPreviousPeriod(): Calendar {
        val cal = currentMonth.value.clone() as Calendar
        when (type) {
            CalendarHeaderType.MONTHS -> {
                cal.add(Calendar.MONTH, -1)
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            CalendarHeaderType.YEARS -> {
                cal.add(Calendar.YEAR, -1)
                cal.set(Calendar.MONTH, Calendar.DECEMBER)
                cal.set(Calendar.DAY_OF_MONTH, 31)
            }
        }
        return cal
    }

    fun getStartOfNextPeriod(): Calendar {
        val cal = currentMonth.value.clone() as Calendar
        when (type) {
            CalendarHeaderType.MONTHS -> {
                cal.add(Calendar.MONTH, 1)
                cal.set(Calendar.DAY_OF_MONTH, 1)
            }
            CalendarHeaderType.YEARS -> {
                cal.add(Calendar.YEAR, 1)
                cal.set(Calendar.MONTH, Calendar.JANUARY)
                cal.set(Calendar.DAY_OF_MONTH, 1)
            }
        }
        return cal
    }

    val prevAllowed = minDate?.let {
        getEndOfPreviousPeriod().after(it) || getEndOfPreviousPeriod().timeInMillis == it.timeInMillis
    } != false

    val nextAllowed = maxDate?.let {
        getStartOfNextPeriod().before(it) || getStartOfNextPeriod().timeInMillis == it.timeInMillis
    } != false

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (prevAllowed) {
            IconButton(
                onClick = if (type == CalendarHeaderType.MONTHS)
                    viewModel::goToPreviousMonth
                else viewModel::decrementYear
            ) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month")
            }
        } else {
            Spacer(modifier = Modifier.size(48.dp))
        }

        TextButton(onClick = viewModel::toggleMonthSelector) {
            Text(
                text = if (type == CalendarHeaderType.MONTHS) SimpleDateFormat(
                    "MMMM yyyy",
                    Locale.getDefault()
                ).format(currentMonth.value.time) else SimpleDateFormat(
                    "yyyy",
                    Locale.getDefault()
                ).format(currentMonth.value.time),
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (nextAllowed) {
            IconButton(
                onClick = if (type == CalendarHeaderType.MONTHS)
                    viewModel::goToNextMonth
                else viewModel::incrementYear
            ) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Next Month")
            }
        } else {
            Spacer(modifier = Modifier.size(48.dp))
        }
    }


}
