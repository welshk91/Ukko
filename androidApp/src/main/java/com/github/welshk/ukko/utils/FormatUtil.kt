package com.github.welshk.ukko.utils

import android.content.res.Resources
import android.view.View
import com.github.welshk.ukko.R
import com.github.welshk.ukko.data.models.openweathermap.details.WeatherDetails
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Class to help with formatting UI elements
 */
class FormatUtil(
    private val resources: Resources
) {
    fun formatTemperature(temperature: Int): String {
        return resources.getString(
            R.string.temp,
            temperature
        )
    }

    fun formatTemp(weatherDetails: WeatherDetails?): String {
        return if (weatherDetails != null) {
            formatTemperature(weatherDetails.main.temp.toInt())
        } else {
            ""
        }
    }

    fun formatTempLow(weatherDetails: WeatherDetails?): String {
        return if (weatherDetails != null) {
            formatTemperature(weatherDetails.main.tempMin.toInt())
        } else {
            ""
        }
    }

    fun formatTempHigh(weatherDetails: WeatherDetails?): String {
        return if (weatherDetails != null) {
            formatTemperature(weatherDetails.main.tempMax.toInt())
        } else {
            ""
        }
    }

    fun formatFeelsLike(weatherDetails: WeatherDetails?): String {
        return if (weatherDetails != null) {
            formatTemperature(weatherDetails.main.feelsLike.toInt())
        } else {
            ""
        }
    }

    fun formatCity(weatherDetails: WeatherDetails?): String {
        return if (weatherDetails?.name != null) {
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

    fun formatDescription(weatherDetails: WeatherDetails?): String {
        return weatherDetails?.weather?.get(0)?.description?.capitalizeWords ?: ""
    }

    fun formatTime(weatherDetails: WeatherDetails?): String {
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