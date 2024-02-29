package com.aless133.commbucks.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class UserPreferences(
    val userName: String?
)

class UserPreferencesRepository (
    private val dataStore: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val USER_NAME = stringPreferencesKey("user_name")
    }

    suspend fun updateUserName(userName: String) = dataStore.edit { preferences ->
        preferences[PreferencesKeys.USER_NAME] = userName
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val userName = preferences[PreferencesKeys.USER_NAME] ?: null
            UserPreferences(userName)
        }


}