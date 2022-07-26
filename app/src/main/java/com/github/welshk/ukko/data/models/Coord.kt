package com.github.welshk.ukko.data.models

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    var longitude: Double,
    @SerializedName("lat")
    var latitude: Double
)
