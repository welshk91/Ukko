package com.github.welshk.ukko.data

import android.location.Location
import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.data.models.WeatherDetails
import com.github.welshk.ukko.networking.RestService
import com.github.welshk.ukko.networking.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.mock.NetworkBehavior
import javax.inject.Singleton

/**
 * Repository class.
 * In more advanced apps, this class would be responsible for getting data from a cache/database or
 * fetching data if not found. Currently we just always fetch data from the internet
 */
@Singleton
class DataRepository {

    /**
     * Don't have any location data.
     * Just get the current weather of the hardcoded stock city
     */
    suspend fun getWeatherDetails(): Response<WeatherDetails> = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(RestService::class.java)
        val response = apiInterface.getCurrentWeather()
        response
    }

    suspend fun getWeatherDetails(location: Location): Response<WeatherDetails> =
        withContext(Dispatchers.IO) {
            if (BuildConfig.USE_MOCK_DATA) {
                val mockRetrofit = RetrofitClient.getMockInstance(NetworkBehavior.create())
                val mockRespinse = mockRetrofit.getCurrentWeather(
                    latitude = location.latitude.toString(),
                    longitude = location.longitude.toString()
                )
                mockRespinse
            } else {
                val retrofit = RetrofitClient.getInstance()
                val apiInterface = retrofit.create(RestService::class.java)
                val response = apiInterface.getCurrentWeather(
                    latitude = location.latitude.toString(),
                    longitude = location.longitude.toString()
                )
                response
            }
        }
}