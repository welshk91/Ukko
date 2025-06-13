package com.github.welshk.ukko.data.models.openweathermap.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    @SerialName("all")
    var all: Int
)