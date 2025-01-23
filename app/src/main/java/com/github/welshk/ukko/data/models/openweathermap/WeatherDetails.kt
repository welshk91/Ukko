package com.github.welshk.ukko.data.models.openweathermap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetails(
    @SerialName("coord")
    var coord: Coord,
    @SerialName("weather")
    var weather: ArrayList<Weather>,
    @SerialName("base")
    var base: String,
    @SerialName("main")
    var main: Main,
    @SerialName("visibility")
    var visibility: Int,
    @SerialName("wind")
    var wind: Wind? = null,
    @SerialName("clouds")
    var clouds: Clouds? = null,
    @SerialName("dt")
    var dt: Long,
    @SerialName("sys")
    var sys: Sys? = null,
    @SerialName("timezone")
    var timezone: Int,
    @SerialName("id")
    var id: Int,
    @SerialName("name")
    var name: String? = null,
    @SerialName("cod")
    var cod: Int
)
