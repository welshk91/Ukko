package com.github.welshk.ukko.data.models

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    var type: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("message")
    var message: Double,
    @SerializedName("country")
    var country: String,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int
)
