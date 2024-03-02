package com.aless133.commbucks.model

import androidx.datastore.preferences.core.stringPreferencesKey

data class UserPreferences(
    val name: String? = null,
    val id: String? = null,
    val email: String? = null,
)

object UserPreferencesKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_ID = stringPreferencesKey("user_id")
    val USER_EMAIL = stringPreferencesKey("user_email")
}

