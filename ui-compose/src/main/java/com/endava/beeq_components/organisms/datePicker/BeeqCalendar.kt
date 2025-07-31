package endava.beeq.compose.organisms.datePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.Calendar


@Composable
fun BeeqCalendarDialog(
    label: String,
    viewModel: BeeqCalendarViewModel,
    modifier: Modifier = Modifier,
    showDialogExternal: Boolean? = null,
    onDismissRequest: (() -> Unit)? = null
) {
    val selectedDates by viewModel.selectedDates.collectAsState()
    val rangeStart by viewModel.rangeStart.collectAsState()
    val rangeEnd by viewModel.rangeEnd.collectAsState()
    val focusManager = LocalFocusManager.current
    val internalDialogState = remember { mutableStateOf(false) }
    val showDialog = showDialogExternal ?: internalDialogState.value

    val displayText = when (viewModel.mode) {
        is DatePickerMode.Single -> selectedDates.firstOrNull()?.toFormattedString().orEmpty()
        is DatePickerMode.Multiple -> selectedDates.sortedBy { it.timeInMillis }
            .joinToString { it.toFormattedString() }

        is DatePickerMode.Range -> {
            val start = rangeStart?.toFormattedString().orEmpty()
            val end = rangeEnd?.toFormattedString().orEmpty()
            if (start.isEmpty() && end.isEmpty()) "" else "$start — $end"
        }
    }

    Column(modifier) {

        OutlinedTextField(
            value = displayText,
            onValueChange = {},
            label = { Text(label) },
            modifier = modifier
                .fillMaxWidth()
                .onFocusEvent { if (it.isFocused) internalDialogState.value = true },
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.CalendarToday, null) },
        )

        if (showDialog) {
            // Reset to day view when dialog is opened
            LaunchedEffect(Unit) {
                viewModel.resetMonthSelector()

                val firstView = if (viewModel.mode is DatePickerMode.Range) {
                    rangeStart
                } else {
                    selectedDates.firstOrNull()
                }

                firstView?.let {
                    viewModel.setMonthTo(it)
                }
            }
            Dialog(onDismissRequest = {
                if (showDialogExternal == null) internalDialogState.value = false
                focusManager.clearFocus(force = true)
                onDismissRequest?.invoke()
            }) {
                Surface(shape = RoundedCornerShape(12.dp), tonalElevation = 4.dp) {
                    BeeqCalendar(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun BeeqCalendar(
    viewModel: BeeqCalendarViewModel,
    modifier: Modifier = Modifier
) {
    val selectedDates by viewModel.selectedDates.collectAsState()
    val rangeStart by viewModel.rangeStart.collectAsState()
    val rangeEnd by viewModel.rangeEnd.collectAsState()
    val month by viewModel.currentMonth.collectAsState()
    val isMonthSelectorOpen by viewModel.isMonthSelectorOpen.collectAsState()
    val days = remember(month) { buildMonthDays(month) }

    Column(modifier.padding(8.dp)) {
        if (isMonthSelectorOpen) {
            BeeqMonthSelector(viewModel = viewModel)
            return@Column
        }

        BeeqCalendarHeader(
            type = CalendarHeaderType.MONTHS,
            viewModel = viewModel
        )

        Row(Modifier.fillMaxWidth()) {
            listOf("M", "T", "W", "T", "F", "S", "S").forEach {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        val rows = days.chunked(7)
        rows.forEach { week ->
            Row(Modifier.fillMaxWidth()) {
                week.forEach { (day, isCurrentMonth) ->
                    val isSelected = selectedDates.any { it.isSameDay(day) }
                    val isInRange = rangeStart != null && rangeEnd != null &&
                            !day.before(rangeStart) && !day.after(rangeEnd)
                    val isEnabled = (viewModel.minDate == null || !day.before(viewModel.minDate)) &&
                            (viewModel.maxDate == null || !day.after(viewModel.maxDate))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                    isInRange -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                                    else -> Color.Transparent
                                }
                            )
                            .clickable(enabled = isEnabled) { viewModel.onDateSelected(day) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${day.get(Calendar.DAY_OF_MONTH)}",
                            color = when {
                                !isEnabled -> Color.LightGray
                                !isCurrentMonth -> LocalContentColor.current.copy(alpha = 0.3f)
                                else -> LocalContentColor.current
                            }
                        )
                    }
                }
            }
        }
    }
}

fun buildMonthDays(month: Calendar): List<Pair<Calendar, Boolean>> {
    val firstDayOfMonth = month.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val currentMonth = firstDayOfMonth.get(Calendar.MONTH)
    val firstDayOfWeek = (firstDayOfMonth.get(Calendar.DAY_OF_WEEK) + 5) % 7
    firstDayOfMonth.add(Calendar.DAY_OF_MONTH, -firstDayOfWeek)
    return List(42) { i ->
        val cal = (firstDayOfMonth.clone() as Calendar).apply { add(Calendar.DAY_OF_MONTH, i) }
        cal to (cal.get(Calendar.MONTH) == currentMonth)
    }
}
