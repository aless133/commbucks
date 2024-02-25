package com.aless133.commbucks.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.viewmodel.compose.viewModel
import com.aless133.commbucks.R
import com.example.commbucks.model.Event

@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = viewModel()
) {
    val state by eventsViewModel.state.collectAsState()
    Log.d("EVENTSSCREEN", state.events.toString())
    Scaffold { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(state.events) { event ->
                EventItem(
                    event = event,
//                modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun EventItem(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
//        modifier = modifier,
        //modifier = modifier
//        border = BorderStroke(width = 1.dp, color = Color.Red)
    ) {
        Text(
            text = event.name,
        )
    }
}
