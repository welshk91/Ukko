package com.github.welshk.ukko.data.models

import com.google.gson.annotations.SerializedName


data class WeatherDetails(
   @SerializedName("coord")
    var coord: Coord,
   @SerializedName("weather")
    var weather: ArrayList<Weather>,
   @SerializedName("base")
    var base: String,
   @SerializedName("main")
    var main: Main,
   @SerializedName("visibility")
    var visibility: Int,
   @SerializedName("wind")
    var wind: Wind? = null,
   @SerializedName("clouds")
    var clouds: Clouds? = null,
   @SerializedName("dt")
    var dt: Int,
   @SerializedName("sys")
    var sys: Sys? = null,
   @SerializedName("timezone")
    var timezone: Int,
   @SerializedName("id")
    var id: Int,
   @SerializedName("name")
    var name: String? = null,
   @SerializedName("cod")
    var cod: Int
)
