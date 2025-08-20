package com.mec.shopping.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun formatDate(millis: Long?, pattern: String = "EEE MMM dd yyyy"): String {//NON-NLS
    if (millis == null) {
        return ""
    }
    val formater = SimpleDateFormat(pattern, Locale.getDefault())

    return formater.format(getLocalDateFromUtcMillis(millis))
}


fun getLocalDateFromUtcMillis(millis: Long): Date {
    val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    utcCalendar.timeInMillis = millis

    val year = utcCalendar.get(Calendar.YEAR)
    val month = utcCalendar.get(Calendar.MONTH)
    val day = utcCalendar.get(Calendar.DAY_OF_MONTH)

    val localCalendar = Calendar.getInstance(TimeZone.getDefault())
    localCalendar.clear()
    localCalendar.set(year, month, day)

    return localCalendar.time
}