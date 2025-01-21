package com.github.welshk.ukko.data

import android.location.Location
import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.networking.Constants
import com.github.welshk.ukko.networking.KtorClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

/**
 * Repository class.
 * In more advanced apps, this class would be responsible for getting data from a cache/database or
 * fetching data if not found. Currently we just always fetch data from the internet
 */
class DataRepository {
    suspend fun getWeatherDetails(
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
}