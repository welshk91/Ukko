package com.github.welshk.ukko.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.app.gradient
import com.github.welshk.ukko.app.radialGradient
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.utils.HeroImageUtil

@Composable
fun HeroImage(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    contentScale: ContentScale = ContentScale.FillBounds,
    contentDescription: String? = null,
    bottomGradientColors: List<Color>? = MaterialTheme.colorScheme.gradient,
    topGradientColors: List<Color>? = MaterialTheme.colorScheme.radialGradient,
) {
    Image(
        modifier = modifier
            .drawWithCache {
                val gradient = bottomGradientColors?.let {
                    Brush.verticalGradient(
                        colors = it,
                        startY = size.height * .7f,
                        endY = size.height
                    )
                }
                val radialGradient = topGradientColors?.let {
                    Brush.radialGradient(
                        colors = it,
                        center = Offset.Zero,
                        radius = size.width * .9f
                    )
                }
                onDrawWithContent {
                    drawContent()
                    radialGradient?.let { drawRect(it, blendMode = BlendMode.Multiply) }
                    gradient?.let { drawRect(it, blendMode = BlendMode.Multiply) }
                }
            },
        painter = painterResource(id),
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Composable
fun HeroImage(
    modifier: Modifier = Modifier,
    heroImage: HeroImage,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    bottomGradientColors: List<Color>? = MaterialTheme.colorScheme.gradient,
    topGradientColors: List<Color>? = MaterialTheme.colorScheme.radialGradient
) {
    HeroImage(
        modifier,
        heroImage.imageDrawable,
        contentScale,
        contentDescription,
        bottomGradientColors,
        topGradientColors
    )
}

@Preview
@Composable
fun HeroImagePreview() {
    UkkoTheme {
        HeroImage(
            heroImage = HeroImageUtil.getPreviewLightWeather()
        )
    }
}