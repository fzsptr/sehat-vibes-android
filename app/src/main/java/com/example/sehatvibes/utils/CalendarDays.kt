package com.example.sehatvibes.utils

import java.time.LocalDate
import java.time.YearMonth

object CalendarDays {
    fun generateCalendarDays(month: YearMonth): List<LocalDate?> {
        val firstDay = month.atDay(1)
        val startOffset = firstDay.dayOfWeek.value % 7
        val totalDays = month.lengthOfMonth()

        val list = mutableListOf<LocalDate?>()

        repeat(startOffset) { list.add(null) }

        for (i in 1..totalDays) {
            list.add(month.atDay(i))
        }

        return list
    }
}