package com.github.welshk.ukko.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UkkoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Colors.Dark.Primary,
            primaryContainer = Colors.Dark.PrimaryContainer,
            onPrimaryContainer = Colors.Dark.OnPrimaryContainer,
            background = Colors.Dark.Surface,
            surface = Colors.Dark.Surface,
            onSurface = Colors.Dark.OnSurface,
            onSurfaceVariant = Colors.Dark.OnSurfaceVariant,
            surfaceContainer = Colors.Dark.SurfaceContainer,
            surfaceDim = Colors.Dark.SurfaceDim,
            surfaceBright = Colors.Dark.SurfaceBright,
            outline = Colors.Dark.Outline,
            outlineVariant = Colors.Dark.OutlineVariant,
            error = Colors.Dark.Error,
            errorContainer = Colors.Dark.ErrorContainer
        )
    } else {
        lightColorScheme(
            primary = Colors.Light.Primary,
            primaryContainer = Colors.Light.PrimaryContainer,
            onPrimaryContainer = Colors.Light.OnPrimaryContainer,
            background = Colors.Light.Surface,
            surface = Colors.Light.Surface,
            onSurface = Colors.Light.OnSurface,
            onSurfaceVariant = Colors.Light.OnSurfaceVariant,
            surfaceDim = Colors.Light.SurfaceDim,
            surfaceBright = Colors.Light.SurfaceBright,
            outline = Colors.Light.Outline,
            outlineVariant = Colors.Light.OutlineVariant,
            surfaceContainer = Colors.Light.SurfaceContainer,
            error = Colors.Light.Error,
            errorContainer = Colors.Light.ErrorContainer
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

val ColorScheme.header: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Colors.Dark.Text else Colors.Light.Text

val ColorScheme.gradient: List<Color>
    @Composable
    get() =
        if (isSystemInDarkTheme()) listOf(
            Colors.Dark.GradientStart,
            Colors.Dark.GradientMiddle,
            Colors.Dark.GradientMiddleEnd,
            Colors.Dark.GradientEnd
        )
        else listOf(
            Colors.Light.GradientStart,
            Colors.Light.GradientMiddle,
            Colors.Light.GradientMiddleEnd,
            Colors.Light.GradientEnd
        )

val ColorScheme.radialGradient: List<Color>
    @Composable
    get() =
        if (isSystemInDarkTheme()) listOf(
            Colors.Dark.RadialGradientStart,
            Colors.Dark.RadialGradientMiddleStart,
            Colors.Dark.RadialGradientMiddle,
            Colors.Dark.RadialGradientMiddleEnd,
            Colors.Dark.RadialGradientEnd
        )
        else listOf(
            Colors.Light.RadialGradientStart,
            Colors.Light.RadialGradientMiddleStart,
            Colors.Light.RadialGradientMiddle,
            Colors.Light.RadialGradientMiddleEnd,
            Colors.Light.RadialGradientEnd
        )