package fr.samneo.thirtydaysoffitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.samneo.thirtydaysoffitness.data.ExerciseList
import fr.samneo.thirtydaysoffitness.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    Scaffold(modifier, {
        AppTopBar()
    }) {
        SwipeableViewWithTwoExerciseCardsInColumn(
            pairNumber = ExerciseList.PairNumber.PAIR1,
            Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        }, modifier
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTheme {
        AppTopBar()
    }
}