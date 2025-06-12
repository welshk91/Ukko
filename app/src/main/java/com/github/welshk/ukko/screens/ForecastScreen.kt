package com.github.welshk.ukko.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.data.models.openweathermap.forecast.City
import com.github.welshk.ukko.data.models.openweathermap.forecast.Coord
import com.github.welshk.ukko.data.models.openweathermap.forecast.Forecast
import com.github.welshk.ukko.data.models.openweathermap.forecast.List
import com.github.welshk.ukko.data.models.openweathermap.forecast.Main
import com.github.welshk.ukko.data.models.openweathermap.forecast.Sys
import com.github.welshk.ukko.data.models.openweathermap.forecast.Weather
import com.github.welshk.ukko.ui.HideSystemBars
import com.github.welshk.ukko.ui.OutlineText
import com.github.welshk.ukko.viewmodels.SlideoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForecastScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: SlideoutViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        SlideoutViewModel.UiState.Error -> Unit
        SlideoutViewModel.UiState.Loading -> Unit
        is SlideoutViewModel.UiState.Success -> {
            HideSystemBars()
            ForecastScreen(
                modifier = modifier,
                daysForecast = uiState.daysForecast
            )
        }
    }
}

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    daysForecast: Forecast
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(daysForecast.list.size) { index ->
                ForecastItem(forecast = daysForecast.list[index])

            }
        }
    }
}

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    forecast: List
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .border(
                1.dp,
                SolidColor(MaterialTheme.colorScheme.onSecondaryContainer),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        forecast.dtTxt?.let {
            OutlineText(text = it)
        }

        Row {
            OutlineText(
                text = forecast.main.temp.toString()
            )
        }

        OutlineText(text = forecast.weather[0].description)
    }
}

@Composable
@PreviewLightDark
fun ForecastItemPreview() {
    UkkoTheme {
        ForecastItem(
            forecast = List(
                dt = null,
                main = Main(
                    temp = 296.76,
                    feelsLike = 296.98,
                    tempMin = 296.76,
                    tempMax = 297.87,
                    pressure = 1015,
                    humidity = 69
                ),
                weather = arrayListOf(
                    Weather(
                        id = 1,
                        main = "Rain",
                        description = "light rain",
                        icon = "10d"
                    )
                ),
                clouds = null,
                wind = null,
                visibility = null,
                pop = null,
                rain = null,
                sys = Sys(
                    pod = "pod"
                ),
                dtTxt = "2022-08-30 15:00:00"
            )
        )
    }
}


@Composable
@PreviewLightDark
private fun ForecastScreenPreview() {
    UkkoTheme {
        ForecastScreen(
            daysForecast = Forecast(
                list = arrayListOf(
                    List(
                        dt = null,
                        main = Main(
                            temp = 296.76,
                            feelsLike = 296.98,
                            tempMin = 296.76,
                            tempMax = 297.87,
                            pressure = 1015,
                            humidity = 69
                        ),
                        weather = arrayListOf(
                            Weather(
                                id = 1,
                                main = "Rain",
                                description = "light rain",
                                icon = "10d"
                            )
                        ),
                        clouds = null,
                        wind = null,
                        visibility = null,
                        pop = null,
                        rain = null,
                        sys = Sys(
                            pod = "pod"
                        ),
                        dtTxt = "2022-08-30 15:00:00"
                    )
                ),
                city = City(
                    coord = Coord(
                        longitude = -82.45,
                        latitude = 27.94
                    )
                )
            )
        )
    }
}