package com.github.welshk.ukko.data.models.openweathermap.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("type")
    var type: Int,
    @SerialName("id")
    var id: Int,
    @SerialName("country")
    var country: String,
    @SerialName("sunrise")
    var sunrise: Int,
    @SerialName("sunset")
    var sunset: Int
)
