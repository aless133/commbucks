package com.aless133.commbucks.ui

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = viewModel()
) {
    val state by eventsViewModel.state.collectAsState()
    Log.d("EVENTSSCREEN",state.events.toString())

    Text(
        text = state.events.toString(),
    )
}

