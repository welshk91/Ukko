package com.github.welshk.ukko.data

import android.location.Location
import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.data.models.openweathermap.forecast.Forecast
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
class ForecastRepository {
    val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast = _forecast.asStateFlow()

    val _httpCodeForecast = MutableStateFlow<HttpStatusCode?>(null)
    val httpCodeForecast = _httpCodeForecast.asStateFlow()

    suspend fun fetchForecast(location: Location) {
        val response = getForecast(location)
        _httpCodeForecast.value = response.status
        if (response.status == HttpStatusCode.OK) {
            val forecast = response.body<Forecast>()
            forecast.let {
                _forecast.value = it
            }
        }
    }

    private suspend fun getForecast(
        location: Location,
        units: String = Constants.UNITS_IMPERIAL
    ): HttpResponse {
        if (BuildConfig.USE_MOCK_DATA) {
            val ktorClient = KtorClient.getMockInstance(json = BuildConfig.MOCK_CURRENT_FORECAST)
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