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
                preferences[USER_NAME]?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[SELECTED_MBTI_KEY] ?: ""
            )
        }
    }

    suspend fun saveUser(username: String,token: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = username
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun saveSelectedMbti(selectedUser: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_MBTI_KEY] = selectedUser
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SharedPreference? = null

        private val USER_NAME = stringPreferencesKey("user_name")
        private val TOKEN_KEY = stringPreferencesKey("token_key")
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