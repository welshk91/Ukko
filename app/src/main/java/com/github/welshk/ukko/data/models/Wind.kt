package com.github.welshk.ukko.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("speed")
    var speed: Double,
    @SerialName("deg")
    var deg: Int
)
