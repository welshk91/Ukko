package com.github.welshk.ukko.data.models.openweathermap.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp")
    var temp: Double,
    @SerialName("feels_like")
    var feelsLike: Double,
    @SerialName("temp_min")
    var tempMin: Double,
    @SerialName("temp_max")
    var tempMax: Double,
    @SerialName("pressure")
    var pressure: Int,
    @SerialName("humidity")
    var humidity: Int
)
