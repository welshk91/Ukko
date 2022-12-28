package com.github.welshk.ukko.networking

import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.data.models.WeatherDetails
import com.github.welshk.ukko.utils.MockUtil
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate


/**
 * Retrofit interface to make API calls
 */
class MockRestService(private val behaviorDelegate: BehaviorDelegate<RestService>) : RestService {

    override suspend fun getCurrentWeather(
        appId: String?,
        latitude: String,
        longitude: String,
        units: String?
    ): Response<WeatherDetails> {
        val json = MockUtil.readJsonFile(javaClass.classLoader, BuildConfig.MOCK_CURRENT_WEATHER)
        val gson = GsonBuilder().create()
        val mockWeatherDetails: WeatherDetails = gson.fromJson(json, WeatherDetails::class.java)
        return behaviorDelegate.returningResponse(mockWeatherDetails)
            .getCurrentWeather(appId, latitude, longitude, units)
    }

    override suspend fun getCurrentWeather(
        appId: String?,
        qCityCountry: String?,
        units: String?
    ): Response<WeatherDetails> {
        val json = MockUtil.readJsonFile(javaClass.classLoader, BuildConfig.MOCK_CURRENT_WEATHER)
        val gson = GsonBuilder().create()
        val mockWeatherDetails: WeatherDetails = gson.fromJson(json, WeatherDetails::class.java)
        return behaviorDelegate.returningResponse(mockWeatherDetails)
            .getCurrentWeather(appId, qCityCountry, units)
    }
}