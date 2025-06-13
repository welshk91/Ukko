package com.github.welshk.ukko.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.welshk.ukko.app.Fonts
import com.github.welshk.ukko.app.UkkoTheme
import com.github.welshk.ukko.app.header
import com.github.welshk.ukko.app.headerOutline
import com.github.welshk.ukko.ui.OutlineText
import io.ktor.http.HttpStatusCode

@Composable
fun DashboardErrorScreen(
    modifier: Modifier = Modifier,
    httpStatusCode: HttpStatusCode?,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (
            errorRef,
        ) = createRefs()

        val errorText =
            httpStatusCode?.description ?: ""

        OutlineText(
            modifier = Modifier
                .constrainAs(errorRef) {
                    centerTo(parent)
                },
            text = errorText,
            fontSize = 32.sp,
            fontFamily = Fonts.ubuntu,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.header,
            colorOutline = MaterialTheme.colorScheme.headerOutline
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun DashboardErrorScreenPreview() {
    UkkoTheme {
        DashboardErrorScreen(
            httpStatusCode = HttpStatusCode.NotFound,
        )
    }
}