package com.github.welshk.ukko.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp

@Composable
fun UkkoCircularProgress(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    trackColor: Color = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
        strokeWidth = strokeWidth,
        strokeCap = strokeCap
    )
}

@Composable
@PreviewLightDark()
private fun UkkoCircularProgressPreview() {
    MaterialTheme {
        UkkoCircularProgress(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}