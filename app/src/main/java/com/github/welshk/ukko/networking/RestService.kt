package com.github.welshk.ukko.networking

import com.github.welshk.ukko.data.models.WeatherDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface to make API calls
 * (Shouldn't hardcode API Keys like this)
 */
interface RestService {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("appid") appId: String? = "41ff78382fe9fc7356ff694b1ec53714",
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String? = "imperial"
    ): Response<WeatherDetails>

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("appid") appId: String? = "41ff78382fe9fc7356ff694b1ec53714",
        @Query("q") qCityCountry: String? = "tampa,usa",
        @Query("units") units: String? = "imperial"
    ): Response<WeatherDetails>
}