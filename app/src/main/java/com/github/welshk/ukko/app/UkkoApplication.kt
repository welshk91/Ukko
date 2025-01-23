package com.github.welshk.ukko.app

import android.app.Application
import com.github.welshk.ukko.data.WeatherRepository
import com.github.welshk.ukko.data.LocationRepository
import com.github.welshk.ukko.viewmodels.DashboardViewModel
import com.github.welshk.ukko.viewmodels.DetailsViewModel
import com.github.welshk.ukko.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module

/**
 * Application class.
 * Only needed this for Koin
 */
@OptIn(KoinExperimentalAPI::class)
class UkkoApplication : Application(), KoinStartup {
    override fun onKoinStartup() = koinConfiguration {
        val appModule = module {
            singleOf(::WeatherRepository)
            singleOf(::LocationRepository)
            viewModelOf(::MainViewModel)
            viewModelOf(::DashboardViewModel)
            viewModelOf(::DetailsViewModel)
        }

        androidContext(this@UkkoApplication)
        modules(appModule)
    }
}