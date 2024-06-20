package com.dermcare.android.di

import android.content.Context
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.local.pref.onboard.OnboardPreference
import com.dermcare.android.data.local.pref.user.UserPreference
import com.dermcare.android.data.local.pref.user.dataStore
import com.dermcare.android.data.remote.api.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val onboardPref = OnboardPreference.getInstance(context.dataStore)
        val userPref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { userPref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return DataRepository.getInstance(apiService, userPref, onboardPref)
    }
}