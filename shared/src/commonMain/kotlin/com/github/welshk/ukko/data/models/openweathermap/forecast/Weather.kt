package com.github.welshk.ukko.data.models.openweathermap.forecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("id")
    var id: Int,
    @SerialName("main")
    var main: String,
    @SerialName("description")
    var description: String,
    @SerialName("icon")
    var icon: String
)
