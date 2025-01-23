package com.github.welshk.ukko.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultJoin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Text with an outline/border
 */
@Composable
fun OutlineText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    colorOutline: Color = Color.Transparent,
    outlineWidth: Float = 2f,
    fontFamily: FontFamily? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    join: StrokeJoin = DefaultJoin
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontFamily = fontFamily,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = colorOutline,
            drawStyle = Stroke(
                width = outlineWidth,
                join = join
            )
        )
    )
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontFamily = fontFamily,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
        )
    )
}

@PreviewLightDark
@Composable
fun OutlineTextPreview() {
    UkkoTheme {
        OutlineText(
            text = "Slightly Cloudy",
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline,
            fontFamily = Fonts.ubuntu,
            fontSize = 42.sp,
            join = StrokeJoin.Bevel
        )
    }
}