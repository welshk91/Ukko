package com.github.welshk.ukko.data.models.openweathermap.forecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("cod")
    var cod: String? = null,
    @SerialName("message")
    var message: Int? = null,
    @SerialName("cnt")
    var cnt: Int? = null,
    @SerialName("list")
    var list: ArrayList<List>,
    @SerialName("city")
    var city: City
)
