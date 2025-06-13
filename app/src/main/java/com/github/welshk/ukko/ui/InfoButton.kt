package com.github.welshk.ukko.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.github.welshk.ukko.R
import com.github.welshk.ukko.app.UkkoTheme

@Composable
fun InfoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color = Color.Unspecified,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource? = null,
    contentDescription: String? = null
) {

    IconButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.info),
            tint = color,
            contentDescription = contentDescription
        )
    }
}

@Composable
@PreviewLightDark
private fun InfoButtonPreview() {
    UkkoTheme {
        InfoButton(
            color = MaterialTheme.colorScheme.surfaceVariant,
            onClick = {}
        )
    }
}