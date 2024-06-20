package com.dermcare.android.data.local.pref.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.username
            preferences[EMAIL_KEY] = user.email
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[USERNAME_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun saveProfileAdditional(user: UserAdditionalModel) {
        dataStore.edit { preference ->
            preference[NAME_KEY] = user.name
            preference[PROFILE_PIC_KEY] = user.profilePic
            preference[AGE_KEY] = user.age
            preference[GENDER_KEY] = user.gender
        }
    }

    fun getProfileAdditional() : Flow<UserAdditionalModel> {
        return dataStore.data.map { preferences ->
            UserAdditionalModel(
                preferences[NAME_KEY] ?: "",
                preferences[PROFILE_PIC_KEY] ?: "",
                preferences[AGE_KEY] ?: "",
                preferences[GENDER_KEY] ?: ""

            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USERNAME_KEY = stringPreferencesKey("username")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        private val PROFILE_PIC_KEY = stringPreferencesKey("pic")
        private val NAME_KEY = stringPreferencesKey("name")
        private val AGE_KEY = stringPreferencesKey("age")
        private val GENDER_KEY = stringPreferencesKey("gender")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}