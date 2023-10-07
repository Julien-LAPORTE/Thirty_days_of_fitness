package fr.samneo.thirtydaysoffitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.samneo.thirtydaysoffitness.data.Datasource
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

@Composable
fun App(modifier: Modifier = Modifier) {

    var columnNumber by remember { mutableStateOf(Datasource.PairNumber.PAIR1) }

    Scaffold(modifier, {
        AppTopBar()
    }) {
        SwipeableContent(
            onSwipeLeft = { columnNumber = Datasource.nextPair(columnNumber) },
            onSwipeRight = { columnNumber = Datasource.previousPair(columnNumber) },
        ) {
            if (it == PaddingValues(top = 64.0.dp)) {
                ViewWithTwoExerciseCardsInColumn(
                    pairNumber = columnNumber, Modifier.padding(it)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    // return true si expended
    var expended by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Row {
                Spacer(Modifier.weight(1f))
                Text(
                    stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(Modifier.weight(1f))
                AppItemButton(expanded = expended, onClick = { expended = !expended })
            }
        }, modifier
    )
    if (expended) {
        DisclaimerWindow(Modifier.padding(dimensionResource(id = R.dimen.padding_disclaimer)))
    }
}

@Composable
fun AppItemButton(
    expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.Close else Icons.Filled.Warning,
            contentDescription = stringResource(id = R.string.disclaimer),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DisclaimerWindow(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(stringResource(id = R.string.disclaimer), style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTheme {
        AppTopBar()
    }
}