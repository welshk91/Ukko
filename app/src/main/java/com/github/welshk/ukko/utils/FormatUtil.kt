package com.github.welshk.ukko.utils

import com.github.welshk.ukko.data.models.WeatherDetails
import java.util.Date

/**
 * Class to help with formatting UI elements to help LiveData
 */
class FormatUtil {
    companion object {
        fun formatTemp(weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                "${weatherDetails.main.temp}F  (${weatherDetails.main.tempMin}F - ${weatherDetails.main.tempMax}F)"
            } else {
                ""
            }
        }

        fun formatCity(weatherDetails: WeatherDetails?): String {
            return "${weatherDetails?.name},"
        }

        //Shouldn't hardcode this string
        fun formatWind(weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                "Wind Up to ${weatherDetails.wind?.speed}mph"
            } else {
                ""
            }
        }

        /**
         * Calculates the Time from the API.
         * The API tends to give a long for time so you must convert it properly to a Date object
         * https://stackoverflow.com/questions/62376115/how-to-obtain-open-weather-api-date-time-from-city-being-fetched
         */
        fun formatTime(weatherDetails: WeatherDetails?): String {
            if (weatherDetails != null) {
                var currentDate = Date(weatherDetails.dt * 1000 + (weatherDetails.timezone * 1000))
                return "Time: ${currentDate}"
            } else {
                return "Time unknown"
            }
        }

        /**
         * https://openweathermap.org/weather-conditions
         */
        fun formatIconUrl(weatherDetails: WeatherDetails?): String? {
            weatherDetails?.let {
                return "http://openweathermap.org/img/wn/${it.weather.get(0).icon}@2x.png"
            }

            return null
        }
    }
}