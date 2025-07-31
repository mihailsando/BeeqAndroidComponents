package endava.beeq.compose.organisms.datePicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun BeeqMonthSelector(viewModel: BeeqCalendarViewModel) {
    val currentMonth = viewModel.currentMonth.collectAsState()
    val currentYear = currentMonth.value.get(Calendar.YEAR)

    Column(Modifier.padding(8.dp)) {
        BeeqCalendarHeader(
            CalendarHeaderType.YEARS,
            viewModel = viewModel
        )

        val months = listOf(
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun",
            "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"
        )

        Column(Modifier.fillMaxWidth()) {
            months.chunked(3).forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    row.forEachIndexed { i, name ->
                        val index = months.indexOf(name)
                        val isMonthInBounds = run {
                            val temp = currentMonth.value.clone() as Calendar
                            temp.set(Calendar.MONTH, index)
                            temp.set(Calendar.DAY_OF_MONTH, 1)
                            temp.applyMidnight()

                            val endOfMonth = temp.clone() as Calendar
                            endOfMonth.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH))
                            endOfMonth.applyMidnight()

                            val afterMin = viewModel.minDate?.let { !endOfMonth.before(it) } ?: true
                            val beforeMax = viewModel.maxDate?.let { !temp.after(it) } ?: true

                            afterMin && beforeMax
                        }

                        if (isMonthInBounds) {
                            TextButton(onClick = { viewModel.selectMonth(index) }) {
                                Text(name)
                            }
                        } else {
                            Box(modifier = Modifier.padding(8.dp)) {
                                Text(name, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}
