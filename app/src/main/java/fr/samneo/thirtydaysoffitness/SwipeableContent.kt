package fr.samneo.thirtydaysoffitness

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class DragAnchors {
    LEFT, MIDDLE, RIGHT
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableContent(
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    content: @Composable () -> Unit
) {
    val endOfDrag = 1500f
    val screenDistanceToValidateSwap = 0.5f
    val durationAnimationSpecMillis = 250

    val density = LocalDensity.current
    val state = remember {
        (AnchoredDraggableState(initialValue = DragAnchors.MIDDLE,
            positionalThreshold = { distance: Float -> distance * screenDistanceToValidateSwap },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(durationAnimationSpecMillis),
            confirmValueChange = { newValue ->
                // Par exemple, si DragAnchors.End est égal à 400f, vous pouvez changer columnNumber
                when (newValue) {
                    DragAnchors.LEFT -> GlobalScope.launch {
                        delay((durationAnimationSpecMillis * 0.60).toLong() ) // Temporisation 60% du temps de l'animation
                        onSwipeLeft()
                    }

                    DragAnchors.RIGHT -> GlobalScope.launch {
                        delay((durationAnimationSpecMillis * 0.60).toLong()) // // Temporisation 60% du temps de l'animation
                        onSwipeRight()
                    }

                    else -> null
                }
                true // Renvoie true pour confirmer le changement
            }

        ))
    }.apply {
        updateAnchors(DraggableAnchors {
            DragAnchors.LEFT at -endOfDrag
            DragAnchors.MIDDLE at 0f
            DragAnchors.RIGHT at endOfDrag
        })
    }

    if ((state.requireOffset() == endOfDrag) || (state.requireOffset() == -endOfDrag)) {
        state.updateAnchors(DraggableAnchors {
            DragAnchors.LEFT at 0f
            DragAnchors.MIDDLE at 0f
            DragAnchors.RIGHT at 0f
        })
    }
    Box(
        Modifier
            .offset {
                IntOffset(
                    x = state
                        .requireOffset()
                        .roundToInt(), y = 0
                )
            }
            .anchoredDraggable(state, Orientation.Horizontal)) {
        content()
    }

}