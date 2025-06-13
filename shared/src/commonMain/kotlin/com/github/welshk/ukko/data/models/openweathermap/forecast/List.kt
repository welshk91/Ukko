package com.github.welshk.ukko.data.models.openweathermap.forecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class List(
    @SerialName("dt")
    var dt: Int? = null,
    @SerialName("main")
    var main: Main,
    @SerialName("weather")
    var weather: ArrayList<Weather> = arrayListOf(),
    @SerialName("clouds")
    var clouds: Clouds? = null,
    @SerialName("wind")
    var wind: Wind? = null,
    @SerialName("visibility")
    var visibility: Int? = null,
    @SerialName("pop")
    var pop: Double? = null,
    @SerialName("rain")
    var rain: Rain? = null,
    @SerialName("sys")
    var sys: Sys,
    @SerialName("dt_txt")
    var dtTxt: String? = null
)
