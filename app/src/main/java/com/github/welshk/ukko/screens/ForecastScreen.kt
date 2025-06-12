package com.github.welshk.ukko.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.app.Fonts
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.app.header
import com.github.welshk.ukko.app.headerOutline
import com.github.welshk.ukko.data.models.openweathermap.forecast.City
import com.github.welshk.ukko.data.models.openweathermap.forecast.Coord
import com.github.welshk.ukko.data.models.openweathermap.forecast.Forecast
import com.github.welshk.ukko.data.models.openweathermap.forecast.List
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

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (
            cityRef,
        ) = createRefs()
        
        OutlineText(
            modifier = Modifier
                .constrainAs(cityRef) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
            text = "Testing forecast view",
            fontSize = 42.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )
    }
}

@Composable
@PreviewLightDark
private fun ForecastScreenPreview() {
    UkkoTheme {
        ForecastScreen(
            daysForecast = Forecast(
                list = arrayListOf(),
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