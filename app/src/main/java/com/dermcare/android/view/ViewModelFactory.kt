package com.dermcare.android.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dermcare.android.data.DataRepository
import com.dermcare.android.di.Injection
import com.dermcare.android.view.auth.login.LoginViewModel
import com.dermcare.android.view.auth.register.RegisterViewModel
import com.dermcare.android.view.camera.preview.PreviewViewModel
import com.dermcare.android.view.diseases.detail.DetailDiseaseViewModel
import com.dermcare.android.view.diseases.DiseasesViewModel
import com.dermcare.android.view.main.MainViewModel
import com.dermcare.android.view.main.bottom_nav.home.HomeViewModel
import com.dermcare.android.view.main.bottom_nav.profile.ProfileViewModel
import com.dermcare.android.view.main.bottom_nav.profile.update.UpdateProfileViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(dataRepository) as T
        }
        if (modelClass.isAssignableFrom(DiseasesViewModel::class.java)) {
            return DiseasesViewModel(dataRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailDiseaseViewModel::class.java)) {
            return DetailDiseaseViewModel(dataRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataRepository) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(PreviewViewModel::class.java)) {
            return PreviewViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(UpdateProfileViewModel::class.java)) {
            return UpdateProfileViewModel(dataRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun clearInstance() {
            DataRepository.clearInstance()
            INSTANCE = null
        }

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}