package fr.samneo.thirtydaysoffitness

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.samneo.thirtydaysoffitness.data.Datasource
import fr.samneo.thirtydaysoffitness.model.Exercise
import fr.samneo.thirtydaysoffitness.ui.theme.AppTheme

@Composable
fun ExerciseCard(exercise: Exercise, modifier: Modifier = Modifier) {
    Card(
        modifier, elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
            Text(
                text = "Jour ${exercise.exerciseNumber}",
                Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
                style = MaterialTheme.typography.displayLarge
            )
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(exercise.title),
                style = MaterialTheme.typography.displayMedium
            )

            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Image(
                    painterResource(exercise.image), null, Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }
            if (exercise.description != null) {
                Text(
                    text = stringResource(id = exercise.description),
                    Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),

                            )
                        .padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ViewWithTwoExerciseCardsInColumn(
    pairNumber: Datasource.PairNumber, modifier: Modifier = Modifier
) {
    val exercises: List<Exercise> = Datasource.collectTheExercisesInPairs(pairNumber)

    Column(
        modifier
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ExerciseCard(
            exercise = exercises[0], Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium),
            )
        )
        Spacer(Modifier.weight(1f))
        ExerciseCard(
            exercise = exercises[1], Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium)
            )
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    AppTheme {
        App()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppDarkPreview() {
    AppTheme(darkTheme = true) {
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