package com.github.welshk.ukko.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.ui.AnimatedSlideOut
import com.github.welshk.ukko.ui.HeroImage
import com.github.welshk.ukko.ui.OutlineText
import com.github.welshk.ukko.utils.HeroImageUtil
import com.github.welshk.ukko.viewmodels.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        DashboardViewModel.UiState.Error -> Unit
        DashboardViewModel.UiState.Loading -> Unit
        is DashboardViewModel.UiState.Success -> {
            DashboardScreen(
                modifier = modifier,
                heroImage = uiState.heroImage,
                city = uiState.city,
                country = uiState.country,
                time = uiState.time,
                description = uiState.description,
                icon = uiState.icon,
                tempLow = uiState.tempLow,
                tempHigh = uiState.tempHigh,
                temp = uiState.temp,
                author = uiState.author,
                site = uiState.site,
                shouldShowForecast = uiState.shouldShowForecast,
                onForecastClicked = viewModel::onForecastClicked
            )
        }
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    heroImage: HeroImage?,
    city: String,
    country: String,
    time: String,
    description: String,
    icon: String,
    tempLow: String,
    tempHigh: String,
    temp: String,
    author: String,
    site: String,
    shouldShowForecast: Boolean,
    onForecastClicked: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (
            heroRef,
            cityRef,
            countryRef,
            timeRef,
            descriptionRef,
            iconRef,
            tempLowRef,
            tempHighRef,
            tempRef,
            authorRef,
            siteRef,
            forecastRef
        ) = createRefs()

        heroImage?.let {
            HeroImage(
                modifier = Modifier
                    .constrainAs(heroRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                heroImage = it
            )
        }

        OutlineText(
            modifier = Modifier
                .constrainAs(cityRef) {
                    top.linkTo(parent.top, margin = 42.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
            text = city,
            fontSize = 42.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(countryRef) {
                    bottom.linkTo(cityRef.bottom)
                    start.linkTo(cityRef.end, margin = 6.dp)
                },
            text = country,
            fontSize = 14.sp,
            fontFamily = Fonts.ubuntu,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(timeRef) {
                    top.linkTo(cityRef.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
            text = time,
            fontSize = 14.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    bottom.linkTo(tempHighRef.top, margin = 6.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
            text = description,
            fontFamily = Fonts.ubuntu,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(tempLowRef) {
                    bottom.linkTo(tempRef.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
            text = tempLow,
            fontSize = 18.sp,
            fontFamily = Fonts.ubuntu,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(tempHighRef) {
                    bottom.linkTo(tempRef.top, margin = 12.dp)
                    start.linkTo(tempLowRef.end, margin = 18.dp)
                },
            text = tempHigh,
            fontSize = 18.sp,
            fontFamily = Fonts.ubuntu,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(tempRef) {
                    bottom.linkTo(parent.bottom, margin = 56.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
                .clickable(enabled = true, onClick = onForecastClicked),
            text = temp,
            fontSize = 96.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(authorRef) {
                    bottom.linkTo(siteRef.top, margin = 4.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                },
            text = author,
            fontSize = 12.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        OutlineText(
            modifier = Modifier
                .constrainAs(siteRef) {
                    bottom.linkTo(parent.bottom, margin = 56.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                },
            text = site,
            fontSize = 12.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )

        AnimatedSlideOut(
            modifier = Modifier
                .constrainAs(forecastRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            visible = shouldShowForecast,
            onDismissRequest = onForecastClicked
        ) {
            ForecastScreenRoute(
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun DashboardScreenPreview() {
    UkkoTheme {
        DashboardScreen(
            heroImage = HeroImageUtil.getPreviewLightWeather(),
            city = "Tampa,",
            country = "USA",
            time = "10:35 PM",
            description = "Overcast Clouds",
            icon = "",
            tempLow = "56",
            tempHigh = "65",
            temp = "61",
            author = "Michelle Mcewen",
            site = "unsplash",
            shouldShowForecast = false
        )
    }
}