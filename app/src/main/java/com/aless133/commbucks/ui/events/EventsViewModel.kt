package com.aless133.commbucks.ui.events

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.*
import com.aless133.commbucks.data.UserPreferencesRepository
import com.aless133.commbucks.model.UserPreferences
import com.aless133.commbucks.model.UserPreferencesKeys
import com.aless133.commbucks.data.getEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class EventsViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _state = MutableStateFlow(EventsUIState(events = getEvents()))
    val state: StateFlow<EventsUIState> = _state.asStateFlow()

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val userName = preferences[UserPreferencesKeys.USER_NAME] ?: null
            UserPreferences(userName)
        }
//    fun updateUserName(userName: String) = {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.edit { preferences ->
//                preferences[UserPreferencesKeys.USER_NAME] = userName
//            }
//        }
//    }

    suspend fun updateUserName(userName: String) = dataStore.edit { preferences ->
        preferences[UserPreferencesKeys.USER_NAME] = userName
    }

}


class EventsViewModelFactory(
    private val dataStore: DataStore<Preferences>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventsViewModel(dataStore = dataStore) as T
    }
}


