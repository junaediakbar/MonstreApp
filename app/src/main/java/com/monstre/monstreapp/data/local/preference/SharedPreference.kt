package com.monstre.monstreapp.data.local.preference

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.monstre.monstreapp.domain.model.User
import kotlinx.coroutines.flow.map

class SharedPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getData(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[USER_KEY] ?: "",
                preferences[SELECTED_MBTI_KEY] ?: "Select a MBTI"
            )
        }
    }

    suspend fun saveUser(user: String) {
        dataStore.edit { preferences ->
            preferences[USER_KEY] = user
        }
    }

    suspend fun saveSelectedUser(selectedUser: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_MBTI_KEY] = selectedUser
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SharedPreference? = null

        private val USER_KEY = stringPreferencesKey("user")
        private val SELECTED_MBTI_KEY = stringPreferencesKey("selected_mbti")

        fun getInstance(dataStore: DataStore<Preferences>): SharedPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}