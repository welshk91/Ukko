package com.github.welshk.ukko.app

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.github.welshk.ukko.R

object Fonts {
    val ubuntu = FontFamily(
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_regular, FontWeight.Normal),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
        Font(R.font.ubuntu_bold, FontWeight.Bold)
    )
}