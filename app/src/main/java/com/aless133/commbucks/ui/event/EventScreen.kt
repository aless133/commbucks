package com.aless133.commbucks.ui.event

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aless133.commbucks.CommbucksScreen
import com.aless133.commbucks.LocalNavController
import com.aless133.commbucks.R
import com.aless133.commbucks.ui.components.FloatingAddButton
import com.aless133.commbucks.ui.components.TopBar
import com.aless133.commbucks.ui.events.EventItem
import com.aless133.commbucks.ui.events.EventsViewModel

@Composable
fun EventScreen(
    eventViewModel: EventViewModel = viewModel()
) {
    val state by eventViewModel.state.collectAsState()
    val navController = LocalNavController.current

    Scaffold(
        topBar = { TopBar(title = "Второй экран") },
    ) { paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        )
        {
            Text(
                text = state.activeTab.name
            )
            Button(
                onClick = {
                    eventViewModel.activateTab(EventTabs.Members)
                },
            ) {
                Text(
                    text = EventTabs.Members.name
                )
            }
            Button(
                onClick = {
                    eventViewModel.activateTab(EventTabs.Operations)
                },
            ) {
                Text(
                    text = EventTabs.Operations.name
                )
            }
        }
    }
}
