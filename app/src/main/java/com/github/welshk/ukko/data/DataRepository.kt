package com.github.welshk.ukko.data

import com.github.welshk.ukko.data.models.WeatherDetails
import com.github.welshk.ukko.networking.RestService
import com.github.welshk.ukko.networking.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Singleton

/**
 * Repository class.
 * In more advanced apps, this class would be responsible for getting data from a cache/database or
 * fetching data if not found. Currently we just always fetch data from the internet
 */
@Singleton
class DataRepository {

    suspend fun getWeatherDetails(): Response<WeatherDetails> = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(RestService::class.java)
        val response = apiInterface.getCurrentWeather()
        response
    }
}