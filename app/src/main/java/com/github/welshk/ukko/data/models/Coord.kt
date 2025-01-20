package com.github.welshk.ukko.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    @SerialName("lon")
    var longitude: Double,
    @SerialName("lat")
    var latitude: Double
)
