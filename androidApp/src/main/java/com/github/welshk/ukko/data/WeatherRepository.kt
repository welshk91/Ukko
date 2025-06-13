package com.github.welshk.ukko.data

import android.location.Location
import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.data.models.openweathermap.details.WeatherDetails
import com.github.welshk.ukko.networking.Constants
import com.github.welshk.ukko.networking.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository class.
 * In more advanced apps, this class would be responsible for getting data from a cache/database or
 * fetching data if not found. Currently we just always fetch data from the internet
 */
class WeatherRepository {
    val _weather = MutableStateFlow<WeatherDetails?>(null)
    val weather = _weather.asStateFlow()

    val _httpCodeWeather = MutableStateFlow<HttpStatusCode?>(null)
    val httpCodeWeather = _httpCodeWeather.asStateFlow()

    //Get the days data in detail
    suspend fun fetchWeather(location: Location) {
        val response = getWeather(location)
        _httpCodeWeather.value = response.status
        if (response.status == HttpStatusCode.OK) {
            val weather = response.body<WeatherDetails>()
            weather.let {
                _weather.value = it
            }
        }
    }

    private suspend fun getWeather(
        location: Location,
        units: String = Constants.UNITS_IMPERIAL
    ): HttpResponse {
        if (BuildConfig.USE_MOCK_DATA) {
            val ktorClient = KtorClient.getMockInstance(json = BuildConfig.MOCK_CURRENT_WEATHER)
            return ktorClient.get("/data/2.5/weather")
        } else {
            val ktorClient = KtorClient.getInstance()
            return ktorClient.get("/data/2.5/weather") {
                url {
                    parameters.append(Constants.APP_ID, BuildConfig.API_KEY)
                    parameters.append(Constants.LATITUDE, location.latitude.toString())
                    parameters.append(Constants.LONGITUDE, location.longitude.toString())
                    parameters.append(Constants.UNITS, units)
                }
            }
        }
    }
}

fun HttpStatusCode.showWarning(): Boolean {
    return this.value !in 200..210
}