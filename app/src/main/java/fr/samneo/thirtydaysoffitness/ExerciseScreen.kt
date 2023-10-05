package fr.samneo.thirtydaysoffitness

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.samneo.thirtydaysoffitness.data.ExerciseList
import fr.samneo.thirtydaysoffitness.model.Exercise
import fr.samneo.thirtydaysoffitness.ui.theme.AppTheme


@Composable
fun SwipeableContent(
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier) { content() }
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

@Composable
fun SwipeableViewWithTwoExerciseCardsInColumn(
    pairNumber: ExerciseList.PairNumber, modifier: Modifier = Modifier
) {
    var currentColumn by remember { mutableStateOf(pairNumber) }
    SwipeableContent(
        onSwipeLeft = { currentColumn = ExerciseList.previousPair(currentColumn) },
        onSwipeRight = { currentColumn = ExerciseList.nextPair(currentColumn) },
        modifier
    ) {
        ViewWithTwoExerciseCardsInColumn(pairNumber = currentColumn)
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