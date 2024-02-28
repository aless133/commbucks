package com.aless133.commbucks.ui.events

import androidx.lifecycle.ViewModel
import com.aless133.commbucks.data.getEvents
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsViewModel : ViewModel() {
    private val _state = MutableStateFlow(EventsState(events = getEvents()))
    val state: StateFlow<EventsState> = _state.asStateFlow()
}