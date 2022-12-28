package com.github.welshk.ukko.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getMockInstance(networkBehavior: NetworkBehavior): RestService {
        val retrofitClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mockRetrofitService = MockRetrofit.Builder(retrofitClient)
            .networkBehavior(networkBehavior)
            .build()

        val delegate: BehaviorDelegate<RestService> = mockRetrofitService.create(
            RestService::class.java
        )

        return MockRestService(delegate)
    }
}