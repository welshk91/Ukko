package com.github.welshk.ukko.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.data.LocationPermission
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.utils.HeroImageUtil
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        DetailsViewModel.UiState.Error -> Unit
        DetailsViewModel.UiState.Loading -> Unit
        is DetailsViewModel.UiState.Success -> {
            HideSystemBars()
            DetailsScreen(
                modifier = modifier,
                onSetPermissionRequest = { viewModel.setPermissionRequest() },
                onLaunchPermissionRequest = viewModel::launchRequest,
                permissionStatus = uiState.permissionStatus,
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
                site = uiState.site
            )
        }
    }
}

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    onSetPermissionRequest: @Composable () -> Unit = {},
    onLaunchPermissionRequest: () -> Unit = {},
    permissionStatus: LocationPermission,
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
    site: String
) {
    onSetPermissionRequest()

    LifecycleStartEffect(key1 = permissionStatus) {
        if (permissionStatus == LocationPermission.NONE) {
            onLaunchPermissionRequest()
        }
        onStopOrDispose { }
    }

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
            siteRef
        ) = createRefs()

        heroImage?.let {
            val gradientColors = MaterialTheme.colorScheme.gradient
            val radialGradientColors = MaterialTheme.colorScheme.radialGradient

            Image(
                modifier = Modifier
                    .constrainAs(heroRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = gradientColors,
                            startY = size.height * .7f,
                            endY = size.height
                        )
                        val radialGradient = Brush.radialGradient(
                            colors = radialGradientColors,
                            center = Offset.Zero,
                            radius = size.width * .9f
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(radialGradient, blendMode = BlendMode.Multiply)
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                painter = painterResource(it.imageDrawable),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }

        OutlineText(
            modifier = Modifier
                .constrainAs(cityRef) {
                    top.linkTo(parent.top, margin = 12.dp)
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
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                },
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
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                },
            text = site,
            fontSize = 12.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )
    }
}

@Composable
@PreviewLightDark
private fun DetailsScreenPreview() {
    UkkoTheme {
        DetailsScreen(
            permissionStatus = LocationPermission.NONE,
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
        )
    }
}