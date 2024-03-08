package com.aless133.commbucks.ui.events

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.*
import com.aless133.commbucks.data.UserPreferencesRepository
import com.aless133.commbucks.model.UserPreferences
import com.aless133.commbucks.data.getEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class EventsViewModel(
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EventsUIState(events = getEvents()))
    val state: StateFlow<EventsUIState> = _state.asStateFlow()

//    val userPreferencesFlow: Flow<UserPreferences> = userPreferencesRepository.userPreferencesFlow
//    suspend fun updateUserName(userName: String) {
//        userPreferencesRepository.updateUserName(userName)
//    }
}


class EventsViewModelFactory(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventsViewModel(userPreferencesRepository = userPreferencesRepository) as T
    }
}


