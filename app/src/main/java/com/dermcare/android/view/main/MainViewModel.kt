package com.dermcare.android.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.local.pref.user.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    fun getOnboard(): LiveData<Boolean> {
        return repository.getFirstOnboard().asLiveData()
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

}