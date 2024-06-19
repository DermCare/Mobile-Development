package com.dermcare.android.view.auth.register

import androidx.lifecycle.ViewModel
import com.dermcare.android.data.DataRepository

class RegisterViewModel(private val repository: DataRepository) : ViewModel() {
    fun register(username: String, email: String, password: String) = repository.register(username, email, password)
}