package com.cesarwillymc.jpmorgantest.util.extension

import com.cesarwillymc.jpmorgantest.util.constants.DELAY_1000
import com.cesarwillymc.jpmorgantest.util.constants.FORMAT_HOUR
import com.cesarwillymc.jpmorgantest.util.constants.FORMAT_TIME_ZONE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Cesar Canaza on 10/4/23.
 * cesarwilly.mc@gmail.com
 *
 * IOWA, United States.
 */

fun Long.convertUnixTimestampToCentralTime(): String {
    val date = Date(this * DELAY_1000)

    val sdf = SimpleDateFormat(FORMAT_HOUR, Locale.US)

    sdf.timeZone = TimeZone.getTimeZone(FORMAT_TIME_ZONE)

    return sdf.format(date)
}
