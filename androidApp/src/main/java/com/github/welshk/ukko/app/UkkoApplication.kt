package com.github.welshk.ukko.app

import android.app.Application
import com.github.welshk.ukko.data.ForecastRepository
import com.github.welshk.ukko.data.LocationRepository
import com.github.welshk.ukko.data.WeatherRepository
import com.github.welshk.ukko.viewmodels.DashboardViewModel
import com.github.welshk.ukko.viewmodels.MainViewModel
import com.github.welshk.ukko.viewmodels.SlideoutViewModel
import com.github.welshk.ukko.viewmodels.TestViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module
import com.github.welshk.ukko.utils.FormatUtil

/**
 * Application class.
 * Only needed this for Koin
 */
@OptIn(KoinExperimentalAPI::class)
class UkkoApplication : Application(), KoinStartup {
    override fun onKoinStartup() = koinConfiguration {
        androidContext(this@UkkoApplication)
        val appModule = module {
            singleOf(::WeatherRepository)
            singleOf(::ForecastRepository)
            singleOf(::LocationRepository)
            viewModelOf(::MainViewModel)
            viewModelOf(::TestViewModel)
            viewModelOf(::DashboardViewModel)
            viewModelOf(::SlideoutViewModel)

            factory {
                FormatUtil(androidContext().resources)
            }
        }

        modules(appModule)
    }
}