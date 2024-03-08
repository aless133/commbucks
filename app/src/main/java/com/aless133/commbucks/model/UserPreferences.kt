package com.aless133.commbucks.model

import androidx.datastore.preferences.core.stringPreferencesKey

data class UserPreferences(
    val name: String? = null,
    val id: String? = null,
    val email: String? = null,
)



