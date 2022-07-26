package com.github.welshk.ukko.utils

import com.github.welshk.ukko.data.models.WeatherDetails

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
    }
}