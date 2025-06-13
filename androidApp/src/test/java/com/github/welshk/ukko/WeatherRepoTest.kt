package com.github.welshk.ukko

import android.location.Location
import com.github.welshk.ukko.data.WeatherRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest

/**
 * Tests for the Weather Repo. Pretty basic right now
 */
class WeatherRepoTest : KoinTest {

    val weatherRepo: WeatherRepository by inject()
    val location = mockk<Location>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                module {
                    single { WeatherRepository() }
                })
        }

        assertNotNull(weatherRepo)

        every { location.latitude } returns 27.94
        every { location.longitude } returns -82.45
    }

    @Test
    fun testGetFakeWeather(): Unit = runBlocking {
        weatherRepo.fetchWeather(location)

        assertNotNull(weatherRepo.weather)
        assertNotNull(weatherRepo._weather)
    }
}