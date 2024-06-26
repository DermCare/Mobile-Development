package com.dermcare.android.view.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.local.pref.user.UserModel
import com.dermcare.android.data.model.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: DataRepository) : ViewModel() {

    fun getOnboard(): LiveData<Boolean> {
        return repository.getFirstOnboard().asLiveData()
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun login(loginRequest: LoginRequest) = repository.login(loginRequest)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}