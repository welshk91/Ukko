package com.github.welshk.ukko.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.data.LocationPermission
import org.koin.androidx.compose.koinViewModel

/**
 * Dashboard, our opening screen.
 * We no longer start this screen; it's currently just a button to go to test
 */
@Composable
fun DashboardScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        DashboardViewModel.UiState.Loading -> Unit
        is DashboardViewModel.UiState.Success -> {
            DashboardScreen(
                modifier = modifier,
                onSetPermissionRequest = { viewModel.setPermissionRequest() },
                buttonText = uiState.buttonText,
                permissionText = uiState.permissionText,
                locationText = uiState.locationText,
                onButtonclicked = viewModel::onButtonClicked
            )
        }
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onSetPermissionRequest: @Composable () -> Unit = {},
    buttonText: String,
    permissionText: String,
    locationText: String,
    onButtonclicked: () -> Unit
) {

    onSetPermissionRequest()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val centerHorizontalGuideline = createGuidelineFromStart(0.5f)
        val centerVerticalGuideline = createGuidelineFromTop(0.5f)
        val (button, permission, location) = createRefs()

        Button(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(permission.start)
                    centerAround(centerHorizontalGuideline)
                    centerAround(centerVerticalGuideline)
                },
            onClick = onButtonclicked,
            content = {
                Text(
                    text = buttonText
                )
            }
        )

        Text(
            modifier = Modifier
                .constrainAs(permission) {
                    top.linkTo(button.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = permissionText
        )

        Text(
            modifier = Modifier
                .constrainAs(location) {
                    top.linkTo(permission.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = locationText
        )
    }
}

@Composable
@Preview
private fun DashboardScreenPreview() {
    UkkoTheme {
        DashboardScreen(
            buttonText = "Get location",
            permissionText = "Has Permission: ${LocationPermission.COURSE}",
            locationText = "Location: -84, 12",
            onButtonclicked = {}
        )
    }
}