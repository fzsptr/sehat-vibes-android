package com.example.sehatvibes.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    fun formatToDate(isoDate: String) : String {
        return try {
            val instant = Instant.parse(isoDate)
            val formatter = DateTimeFormatter.ofPattern(
                "dd MMMM yyyy",
                Locale("id", "ID")
            )
            formatter.format(instant.atZone(ZoneId.systemDefault()))
        } catch (e: Exception) {
            "-"
        }
    }
}