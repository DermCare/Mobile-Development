package com.dermcare.android.data.local.pref.onboard

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isFistOnboard")

class OnboardPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveFirstOnboard() {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_KEY] = false
        }
    }

    fun getFirstOnboard(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_FIRST_KEY] ?: true
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: OnboardPreference? = null

        private val IS_FIRST_KEY = booleanPreferencesKey("isFirstOnboard")

        fun getInstance(dataStore: DataStore<Preferences>): OnboardPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = OnboardPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}