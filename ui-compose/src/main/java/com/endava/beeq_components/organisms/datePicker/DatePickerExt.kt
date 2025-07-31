package endava.beeq.compose.organisms.datePicker

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.applyMidnight() = apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Calendar.isSameDay(other: Calendar): Boolean =
    get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)

fun Calendar.toFormattedString(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(time)