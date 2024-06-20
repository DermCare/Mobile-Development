package com.dermcare.android.data.local.pref.user

data class UserModel(
    val username: String,
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)