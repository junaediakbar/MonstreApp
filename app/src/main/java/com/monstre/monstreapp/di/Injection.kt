package com.monstre.monstreapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.data.MonstreRepository
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.api.ApiConfig
import com.monstre.monstreapp.data.remote.api.ApiConfigSmartWatch
import com.monstre.monstreapp.data.remote.api.ApiServiceSmartWatch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService()
        val pref = SharedPreference.getInstance(context.dataStore)
        return AuthRepository(pref, apiService)
    }

    fun provideMonstreeRepository(context: Context): MonstreRepository{
        val apiService = ApiConfig.getApiService()
        val pref = SharedPreference.getInstance(context.dataStore)
        val apiServiceSmartWatch = ApiConfigSmartWatch.getApiService()
        return MonstreRepository(pref, apiService,apiServiceSmartWatch)
    }
}