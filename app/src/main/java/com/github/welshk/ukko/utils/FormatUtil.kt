package com.github.welshk.ukko.utils

import android.view.View
import com.github.welshk.ukko.R
import com.github.welshk.ukko.data.models.WeatherDetails
import java.util.*

/**
 * Class to help with formatting UI elements to help LiveData
 */
class FormatUtil {
    companion object {
        fun formatTemp(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                view.context.getString(
                    R.string.temp_range,
                    weatherDetails.main.temp.toInt(),
                    weatherDetails.main.tempMin.toInt(),
                    weatherDetails.main.tempMax.toInt()
                )
            } else {
                ""
            }
        }

        fun formatCity(weatherDetails: WeatherDetails?): String {
            return "${weatherDetails?.name},"
        }

        //Shouldn't hardcode this string
        fun formatWind(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails?.wind != null) {
                view.context.getString(R.string.wind_speeds, weatherDetails.wind?.speed?.toInt())
            } else {
                ""
            }
        }

        fun formatTime(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                view.context.getString(
                    R.string.time_stamp,
                    formatDate(weatherDetails.dt, weatherDetails.timezone).toString()
                )
            } else {
                ""
            }
        }

        /**
         * Calculates the Time Stamp from the API.
         * The API tends to give a long for time so you must convert it properly to a Date object
         * https://stackoverflow.com/questions/62376115/how-to-obtain-open-weather-api-date-time-from-city-being-fetched
         */
        fun formatDate(dt: Long, timezone: Int): Date {
            return Date(dt * 1000 + (timezone * 1000))
        }

        /**
         * https://openweathermap.org/weather-conditions
         * Gets a 504 Gateway error for some reason...
         */
        fun formatIconUrl(weatherDetails: WeatherDetails?): String? {
            weatherDetails?.let {
                return "http://openweathermap.org/img/wn/${it.weather.get(0).icon}@2x.png"
            }

            return null
        }
    }
}