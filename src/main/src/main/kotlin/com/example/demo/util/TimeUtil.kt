package com.example.demo.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


class TimeUtil {
    companion object {

        fun getDateTimeNow(): LocalDateTime {
            return toUtc(LocalDateTime.now())
        }

        fun toZone(time: LocalDateTime, fromZone: ZoneId?, toZone: ZoneId?): LocalDateTime {
            val zonedtime = time.atZone(fromZone)
            val converted = zonedtime.withZoneSameInstant(toZone)
            return converted.toLocalDateTime()
        }

        fun toZone(time: LocalDateTime, toZone: ZoneId?): LocalDateTime {
            return toZone(time, ZoneId.systemDefault(), toZone)
        }

        fun toUtc(time: LocalDateTime, fromZone: ZoneId?): LocalDateTime {
            return toZone(time, fromZone, ZoneOffset.UTC)
        }

        fun toUtc(time: LocalDateTime): LocalDateTime {
            return toUtc(time, ZoneId.systemDefault())
        }

    }
}
