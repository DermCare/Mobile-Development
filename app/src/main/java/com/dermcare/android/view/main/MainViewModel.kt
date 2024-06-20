package com.dermcare.android.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.local.pref.user.UserModel
import com.dermcare.android.data.remote.response.DetailDiseaseItem
import com.dermcare.android.data.remote.response.UserData
import com.dermcare.android.data.remote.response.UserResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    fun getOnboard(): LiveData<Boolean> {
        return repository.getFirstOnboard().asLiveData()
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getUser(): LiveData<ResultData<UserResponse>> {
        Log.d("GET USER API", "INI MASUK KE GET USER API")
        return repository.getUser()
    }

}