package com.aless133.commbucks.ui.event

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aless133.commbucks.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EventViewModel(
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EventUIState(activePage = 0))
    val uiState: StateFlow<EventUIState> = _uiState.asStateFlow()

    fun activatePage (page: Int) {
        _uiState.update { currentState ->
            currentState.copy(activePage = page)
        }
    }
}

class EventViewModelFactory(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventViewModel(userPreferencesRepository = userPreferencesRepository) as T
    }
}