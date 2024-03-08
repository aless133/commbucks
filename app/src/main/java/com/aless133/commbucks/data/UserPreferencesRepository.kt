package com.aless133.commbucks.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aless133.commbucks.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val USER_PREFERENCES_NAME = "user_preferences"
private val Context.userDataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

class UserPreferencesRepository(private val userDataStore: DataStore<Preferences>) {

    private object UserPreferencesKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_ID = stringPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    val userPreferencesFlow: Flow<UserPreferences> = userDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val userName = preferences[UserPreferencesKeys.USER_NAME]
            UserPreferences(userName)
        }
//    suspend fun isLogged(): Boolean {
//        val isLoggedIn = userDataStore.data.map { preferences ->
//            preferences[UserPreferencesKeys.USER_NAME] ?: ""
//        }.map { isLoggedIn ->
//            isLoggedIn.isNotEmpty()
//        }.first()
//        return isLoggedIn
//    }

    suspend public fun updateUserName(userName: String) = userDataStore.edit { preferences ->
        preferences[UserPreferencesKeys.USER_NAME] = userName
    }
}