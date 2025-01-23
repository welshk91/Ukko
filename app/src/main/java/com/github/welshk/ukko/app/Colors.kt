package com.github.welshk.ukko.app

import androidx.compose.ui.graphics.Color

object Colors {
    private val LightPurple = Color(0xFFBB86FC)
    private val Purple = Color(0xFF6200EE)
    private val DarkPurple = Color(0xFF3700B3)

    private val VeryDarkBrown = Color(0xFF2F2A27)

    private val NearBlack = Color(0xFF0A0A0A)
    private val VeryDarkGray = Color(0xFF45423F)
    private val DarkGray = Color(0xFF5A5653)
    private val LightGray = Color(0xFF949494)
    private val VeryLightGray = Color(0xFFD9D9D9)
    private val VeryVeryLightGray = Color(0xFFF0F0F0)
    private val OffWhite = Color(0xFFF9F9F9)
    private val White = Color(0xFFFFFFFF)

    private val MediumDarkGold = Color(0xFF543F10)
    private val Gold = Color(0xff976D02)
    private val LightGold = Color(0xFFC18C02)
    private val MediumTan = Color(0xFFFFF6E9)
    private val LightTan = Color(0xFFFFFBF5)
    private val VeryDarkTan = Color(0xFF202325)

    private val Red = Color(0xFF8D1F1F)
    private val RedMostlyTransparent = Color(0x0F8D1F1F)

    private val LightBlue = Color(0xFFD4F6FF)
    private val Blue = Color(0xFF7CE7FF)

    object Light {
        val Surface = White
        val SurfaceContainer = LightTan
        val SurfaceDim = VeryLightGray
        val SurfaceBright = VeryVeryLightGray
        val OnSurface = VeryDarkBrown
        val OnSurfaceVariant = DarkGray
        val Primary = Purple
        val PrimaryContainer = MediumTan
        val OnPrimaryContainer = OnSurfaceVariant
        val Outline = LightGray
        val OutlineVariant = Color.Black
        val Error = Red
        val ErrorContainer = RedMostlyTransparent

        // Custom
        val Text = Dark.Text
        val TextOutline = Dark.TextOutline
        val BackgroundGradiant = Dark.BackgroundGradiant

        val GradientStart = Dark.GradientStart
        val GradientMiddleStart = Dark.GradientMiddleStart
        val GradientMiddle = Dark.GradientMiddle
        val GradientMiddleEnd = Dark.GradientMiddleEnd
        val GradientEnd = Dark.GradientEnd

        val RadialGradientStart = Dark.RadialGradientStart
        val RadialGradientMiddleStart = Dark.RadialGradientMiddleStart
        val RadialGradientMiddle = Dark.RadialGradientMiddle
        val RadialGradientMiddleEnd = Dark.RadialGradientMiddleEnd
        val RadialGradientEnd = Dark.RadialGradientEnd

        val ProgressBar = Blue
    }

    object Dark {
        val Surface = NearBlack
        val SurfaceContainer = VeryDarkTan
        val SurfaceDim = DarkGray
        val SurfaceBright = VeryDarkGray
        val OnSurface = OffWhite
        val OnSurfaceVariant = VeryLightGray
        val Primary = LightPurple
        val PrimaryContainer = MediumDarkGold
        val OnPrimaryContainer = OnSurfaceVariant
        val Outline = DarkGray
        val OutlineVariant = Color.White
        val Error = Red
        val ErrorContainer = RedMostlyTransparent

        // Custom
        val Text = OnSurface
        val TextOutline = Surface.copy(alpha = .8f)
        val BackgroundGradiant = Surface

        val GradientStart = Color(0x00131313)
        val GradientMiddleStart = Color(0x66131313)
        val GradientMiddle = Color(0xB3131313)
        val GradientMiddleEnd = Color(0xE6131313)
        val GradientEnd = Color(0xFF131313)

        val RadialGradientStart = Color(0xFF131313)
        val RadialGradientMiddleStart = Color(0xCC131313)
        val RadialGradientMiddle = Color(0x99131313)
        val RadialGradientMiddleEnd = Color(0x33131313)
        val RadialGradientEnd = Color(0x00131313)

        val ProgressBar = LightBlue
    }
}