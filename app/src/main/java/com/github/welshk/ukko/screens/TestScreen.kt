package com.github.welshk.ukko.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.data.LocationPermission
import com.github.welshk.ukko.viewmodels.TestViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Test, our opening screen.
 * We no longer start this screen; it's currently just a button to go to test
 */
@Composable
fun TestScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        TestViewModel.UiState.Loading -> Unit
        is TestViewModel.UiState.Success -> {
            TestScreen(
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
fun TestScreen(
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
private fun TestScreenPreview() {
    UkkoTheme {
        TestScreen(
            buttonText = "Get location",
            permissionText = "Has Permission: ${LocationPermission.COURSE}",
            locationText = "Location: -84, 12",
            onButtonclicked = {}
        )
    }
}