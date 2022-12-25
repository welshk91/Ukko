package com.github.welshk.ukko.utils

import java.util.*

object DateUtil {
    private const val DAWN = 6
    private const val DUSK = 22

    /**
     * Calculates the Time Stamp from the API.
     * The API tends to give a long for time so you must convert it properly to a Date object
     * https://stackoverflow.com/questions/62376115/how-to-obtain-open-weather-api-date-time-from-city-being-fetched
     * Time stamp is incorrect if you add in timezone
     */
    fun getDate(dt: Long, timezone: Int): Date {
        return Date(dt * 1000)
    }

    fun getCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal
    }

    /**
     * 0 Night, 1 Day
     */
    fun getDayOrNight(dt: Long): Int {
        val date = getDate(dt, 0)
        val cal = getCalendar(date)

        return if (cal.get(Calendar.HOUR_OF_DAY) < DAWN || cal.get(Calendar.HOUR_OF_DAY) > DUSK) {
            //Night
            0
        } else {
            //Day
            1
        }
    }
}