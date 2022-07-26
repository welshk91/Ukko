package com.github.welshk.ukko.data.models

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    var speed: Double,
    @SerializedName("deg")
    var deg: Int
)
