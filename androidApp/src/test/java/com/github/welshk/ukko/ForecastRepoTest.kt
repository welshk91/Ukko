package com.github.welshk.ukko

import android.location.Location
import com.github.welshk.ukko.data.ForecastRepository
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
 * Tests for the Forecast Repo. Pretty basic right now
 */
class ForecastRepoTest : KoinTest {

    val forecastRepo: ForecastRepository by inject()
    val location = mockk<Location>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                module {
                    single { ForecastRepository() }
                })
        }

        assertNotNull(forecastRepo)

        every { location.latitude } returns 27.94
        every { location.longitude } returns -82.45
    }

    @Test
    fun testGetFakeWeather(): Unit = runBlocking {
        forecastRepo.fetchForecast(location)

        assertNotNull(forecastRepo.forecast)
        assertNotNull(forecastRepo._forecast)
    }
}