package fr.samneo.thirtydaysoffitness

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import fr.samneo.thirtydaysoffitness.data.ExerciseList
import fr.samneo.thirtydaysoffitness.model.Exercise
import fr.samneo.thirtydaysoffitness.ui.theme.AppTheme
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
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val endOfDrag = 1500f
    val screenDistanceToValidateSwap = 0.5f
    val durationAnimationSpecMillis = 250

    var columnNumber by remember { mutableStateOf(ExerciseList.PairNumber.PAIR1) }

    val density = LocalDensity.current
    var state = remember {
        (AnchoredDraggableState(initialValue = DragAnchors.MIDDLE,
            positionalThreshold = { distance: Float -> distance * screenDistanceToValidateSwap },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(durationAnimationSpecMillis),
            confirmValueChange = { newValue ->
                // Par exemple, si DragAnchors.End est égal à 400f, vous pouvez changer columnNumber
                when (newValue) {
                    DragAnchors.LEFT -> GlobalScope.launch {
                        delay((durationAnimationSpecMillis * 0.60).toLong() ) // Temporisation 60% du temps de l'animation
                        columnNumber = ExerciseList.nextPair(columnNumber)
                    }

                    DragAnchors.RIGHT -> GlobalScope.launch {
                        delay((durationAnimationSpecMillis * 0.60).toLong()) // // Temporisation 60% du temps de l'animation
                        columnNumber = ExerciseList.previousPair(columnNumber)
                    }

                    else -> {}
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
        ViewWithTwoExerciseCardsInColumn(pairNumber = columnNumber, modifier)
    }

}

@Composable
fun ExerciseCard(exercice: Exercise, modifier: Modifier = Modifier) {
    Card(
        modifier, elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Text(
            text = "Jour ${exercice.exerciseNumber}",
            Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
            style = MaterialTheme.typography.displayLarge
        )
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(exercice.title),
                style = MaterialTheme.typography.displayMedium
            )
            Image(
                painterResource(exercice.image),
                null,
                Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                    .size(dimensionResource(id = R.dimen.image_size)),
            )
            Text(
                text = stringResource(id = exercice.description), Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_small)
                ), style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ViewWithTwoExerciseCardsInColumn(
    pairNumber: ExerciseList.PairNumber, modifier: Modifier = Modifier
) {
    val exercices: List<Exercise> = ExerciseList.collectTheExercisesInPairs(pairNumber)

    Column(
        modifier
    ) {
        ExerciseCard(
            exercice = exercices[0], Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium),
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium)
            )
        )
        ExerciseCard(
            exercice = exercices[1], Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium),
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium)
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    AppTheme {
        App()
    }
}

@Preview
@Composable
fun ExerciseCardPreview() {
    AppTheme {
        ExerciseCard(
            Exercise(
                1, R.string.exercice1_title, R.drawable.exercise1, R.string.exercice1_description
            )
        )
    }
}