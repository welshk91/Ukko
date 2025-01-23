package com.github.welshk.ukko.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.welshk.ukko.app.Fonts
import com.github.welshk.ukko.app.UkkoTheme

@Composable
fun UkkoButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = Color.Unspecified,
    fontFamily: FontFamily? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = color,
            )
        )
    }
}

@Composable
@PreviewLightDark
private fun UkkoButtonPreview() {
    UkkoTheme {
        UkkoButton(
            text = "Go To System Settings",
            onClick = {},
            fontFamily = Fonts.ubuntu,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}