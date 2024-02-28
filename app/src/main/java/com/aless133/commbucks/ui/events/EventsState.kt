package com.aless133.commbucks.ui.events

import com.example.commbucks.model.Event

data class EventsState(
    val events: List<Event> = emptyList(),
)
