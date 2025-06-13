package com.github.welshk.ukko.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun AnimatedSlideOut(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.CenterEnd,
    visible: Boolean,
    enter: EnterTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(
            durationMillis = 400,
            easing = EaseOut
        )
    ),
    exit: ExitTransition = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(
            durationMillis = 200,
            easing = EaseIn
        )
    ),
    popupProperties: PopupProperties = PopupProperties(focusable = true),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable (AnimatedVisibilityScope.() -> Unit),
) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = visible

    if (visibleState.currentState || visibleState.targetState || !visibleState.isIdle) {
        Popup(
            alignment,
            onDismissRequest = onDismissRequest,
            properties = popupProperties
        ) {
            AnimatedVisibility(
                visibleState = visibleState,
                enter = enter,
                exit = exit,
                modifier = modifier,
                content = content
            )
        }
    }
}