package com.aless133.commbucks.ui.event

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EventViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EventUIState(activePage = 0))
    val uiState: StateFlow<EventUIState> = _uiState.asStateFlow()

    fun activatePage (page: Int) {
        _uiState.update { currentState ->
            currentState.copy(activePage = page)
        }
    }
}