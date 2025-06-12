package com.github.welshk.ukko.data

import android.location.Location
import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.data.models.openweathermap.details.WeatherDetails
import com.github.welshk.ukko.data.models.openweathermap.forecast.WeatherForecast
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
    val _weatherDetails = MutableStateFlow<WeatherDetails?>(null)
    val weatherDetails = _weatherDetails.asStateFlow()

    val _httpCodeDetails = MutableStateFlow<HttpStatusCode?>(null)
    val httpCodeDetails = _httpCodeDetails.asStateFlow()

    val _weatherForecast = MutableStateFlow<WeatherForecast?>(null)
    val weatherForecast = _weatherForecast.asStateFlow()

    val _httpCodeForecast = MutableStateFlow<HttpStatusCode?>(null)
    val httpCodeForecast = _httpCodeForecast.asStateFlow()

    //Get the days data in detail
    suspend fun fetchWeatherDetails(location: Location) {
        val response = getWeatherDetails(location)
        _httpCodeDetails.value = response.status
        if (response.status == HttpStatusCode.OK) {
            val details = response.body<WeatherDetails>()
            details.let {
                _weatherDetails.value = it
            }
        }
    }

    private suspend fun getWeatherDetails(
        location: Location,
        units: String = Constants.UNITS_IMPERIAL
    ): HttpResponse {
        if (BuildConfig.USE_MOCK_DATA) {
            val ktorClient = KtorClient.getMockInstance()
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

    suspend fun fetchWeatherForecast(location: Location) {
        val response = getWeatherForecast(location)
        _httpCodeForecast.value = response.status
        if (response.status == HttpStatusCode.OK) {
            val forecast = response.body<WeatherForecast>()
            forecast.let {
                _weatherForecast.value = it
            }
        }
    }

    private suspend fun getWeatherForecast(
        location: Location,
        units: String = Constants.UNITS_IMPERIAL
    ): HttpResponse {
        if (BuildConfig.USE_MOCK_DATA) {
            val ktorClient = KtorClient.getMockInstance()
            return ktorClient.get("/data/2.5/forecast")
        } else {
            val ktorClient = KtorClient.getInstance()
            return ktorClient.get("/data/2.5/forecast") {
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