package pl.mkonkel.wsb.pam.chatapp.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun getLocalDateTimeInHumaanReadableFormat(): String {
        val now = LocalDateTime.now()

        return now.format(dateFormat)
    }
}