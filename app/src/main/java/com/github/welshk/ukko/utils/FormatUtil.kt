package com.github.welshk.ukko.utils

import android.view.View
import com.github.welshk.ukko.R
import com.github.welshk.ukko.data.models.WeatherDetails
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Class to help with formatting UI elements to help LiveData
 */
class FormatUtil {
    companion object {
        fun formatTemperature(view: View, temperature: Int): String {
            return view.context.getString(
                R.string.temp,
                temperature
            )
        }

        fun formatTemp(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                formatTemperature(view, weatherDetails.main.temp.toInt())
            } else {
                ""
            }
        }

        fun formatTempLow(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                formatTemperature(view, weatherDetails.main.tempMin.toInt())
            } else {
                ""
            }
        }

        fun formatTempHigh(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                formatTemperature(view, weatherDetails.main.tempMax.toInt())
            } else {
                ""
            }
        }

        fun formatFeelsLike(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                formatTemperature(view, weatherDetails.main.feelsLike.toInt())
            } else {
                ""
            }
        }

        fun formatCity(weatherDetails: WeatherDetails?): String {
            return if (weatherDetails?.wind != null) {
                "${weatherDetails.name},"
            } else {
                ""
            }
        }

        fun formatCountry(weatherDetails: WeatherDetails?): String {
            return if (weatherDetails != null) {
                "${weatherDetails.sys?.country}"
            } else {
                ""
            }
        }

        //Shouldn't hardcode this string
        fun formatWind(view: View, weatherDetails: WeatherDetails?): String {
            return if (weatherDetails?.wind != null) {
                view.context.getString(R.string.wind_speeds, weatherDetails.wind?.speed?.toInt())
            } else {
                ""
            }
        }

        fun formatDescription(view: View, weatherDetails: WeatherDetails?): String {
            return weatherDetails?.weather?.get(0)?.description?.capitalizeWords ?: ""
        }

        fun formatTime(view: View, weatherDetails: WeatherDetails?): String {
            val timeFormat: DateFormat = SimpleDateFormat("h:mm a")
            return if (weatherDetails != null) {
                val date = DateUtil.getDate(weatherDetails.dt, weatherDetails.timezone)
                return timeFormat.format(date)
            } else {
                ""
            }
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