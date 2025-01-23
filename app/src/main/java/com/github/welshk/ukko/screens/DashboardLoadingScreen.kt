package com.github.welshk.ukko.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.welshk.ukko.app.Fonts
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.data.LocationPermission
import com.github.welshk.ukko.ui.UkkoButton
import com.github.welshk.ukko.ui.UkkoCircularProgress

@Composable
fun DashboardLoadingScreen(
    modifier: Modifier = Modifier,
    permissionStatus: LocationPermission,
    goToSystemSettingsText: String,
    onSetPermissionRequest: @Composable () -> Unit = {},
    onGoToSystemSettingsClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (
            progressbarRef,
            buttonRef,
        ) = createRefs()

        if (permissionStatus != LocationPermission.NONE) {
            UkkoCircularProgress(
                modifier = Modifier
                    .constrainAs(progressbarRef) {
                        centerTo(parent)
                    }
            )
        } else {
            onSetPermissionRequest()

            UkkoButton(
                modifier = Modifier
                    .constrainAs(buttonRef) {
                        centerTo(parent)
                    },
                text = goToSystemSettingsText,
                fontFamily = Fonts.ubuntu,
                fontSize = 18.sp,
                onClick = onGoToSystemSettingsClicked
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun DashboardLoadingScreenPreview() {
    UkkoTheme {
        DashboardLoadingScreen(
            permissionStatus = LocationPermission.NONE,
            goToSystemSettingsText = "Go to system settings",
            onGoToSystemSettingsClicked = {}
        )
    }
}