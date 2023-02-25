package dev.rafaz.plugins

import kotlinx.datetime.LocalDateTime
import java.util.*

fun LocalDateTime.Companion.now(): LocalDateTime {
    val now = Calendar.getInstance()
    return LocalDateTime(
        year = now[Calendar.YEAR],
        monthNumber = now[Calendar.MONTH] + 1,
        dayOfMonth = now[Calendar.DAY_OF_MONTH],
        hour = now[Calendar.HOUR_OF_DAY],
        minute = now[Calendar.MINUTE],
        second = now[Calendar.SECOND],
        nanosecond = now[Calendar.MILLISECOND]
    )
}