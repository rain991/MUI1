package com.example.mui1.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun Date.formatDateWithYear(isLongDayOfWeekName: Boolean = true): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance().apply {
        time = this@formatDateWithYear
    }
    val dayOfWeekName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, if (isLongDayOfWeekName) Calendar.LONG else Calendar.SHORT, locale)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale)
    val year = calendar.get(Calendar.YEAR)
    return "$dayOfWeekName, $dayOfMonth $month $year"
}
