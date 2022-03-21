package com.example.weatherapplication.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


fun Long.toDateString(timex: Long, timezone: Int): String {
    val date = Date(timex *1000L)
    val sdf = SimpleDateFormat("HH:mm", Locale.UK)
    val utc = timezone/3600
    var gmt = utc.toString()
    if (utc >= 0) {
        gmt = "+${utc}"
    }
    sdf.timeZone = TimeZone.getTimeZone("GMT$gmt:00")
    return sdf.format(date)
}
