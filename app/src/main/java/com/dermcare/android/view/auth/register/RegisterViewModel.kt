package com.dermcare.android.view.auth.register

import androidx.lifecycle.ViewModel
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.model.request.RegisterRequest

class RegisterViewModel(private val repository: DataRepository) : ViewModel() {
    fun register(registerRequest: RegisterRequest) = repository.register(registerRequest)
}