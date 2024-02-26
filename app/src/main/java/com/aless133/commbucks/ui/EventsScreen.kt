package com.aless133.commbucks.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aless133.commbucks.CommbucksScreen
import com.aless133.commbucks.LocalNavController
import com.aless133.commbucks.R
import com.aless133.commbucks.ui.theme.CommbucksTheme
import com.example.commbucks.model.Event

@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = viewModel()
) {
    val state by eventsViewModel.state.collectAsState()
    Log.d("EVENTSSCREEN", state.events.toString())
    Scaffold(
        topBar = { EventsTopBar() },
        floatingActionButton = { EventsAddButton() }
//        modifier = Modifier
//            .padding(dimensionResource(R.dimen.padding_small))
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            modifier = Modifier.padding(
                vertical = dimensionResource(R.dimen.padding_medium),
                horizontal = dimensionResource(R.dimen.padding_small)
            ),
        ) {
            items(state.events) { event ->
                EventItem(event = event)
            }
        }
    }
}

@Composable
fun EventsTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(dimensionResource(R.dimen.padding_small)),
    ) {
        Text(
            text = stringResource(R.string.app_name),
//            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium,
        )
//        IconButton(
//            onClick = onShareButtonClicked,
//            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Share,
//                contentDescription = stringResource(R.string.share),
//                tint = MaterialTheme.colorScheme.onPrimary
//            )
//        }
    }
}

@Composable
fun EventsAddButton() {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = { },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun EventItem(event: Event) {
    val navController = LocalNavController.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
//            .padding(dimensionResource(id = R.dimen.padding_small))
    )
    //        modifier = modifier,
    //modifier = modifier
//        border = BorderStroke(width = 1.dp, color = Color.Red)
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.titleLarge,
                    //modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
                Row(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                )
                {
                    Text(
                        text = stringResource(R.string.event_count, event.count),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.width(130.dp),
                    )
                    Text(
                        text = stringResource(R.string.event_budget, event.budget),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
            IconButton(
                onClick = {
                    Log.d("NAVIGATE", CommbucksScreen.Event.name);
                    Log.d("NAVIGATE", navController.toString());
                    navController.navigate(CommbucksScreen.Event.name);
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Expand",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    CommbucksTheme {
        EventsScreen()
    }
}