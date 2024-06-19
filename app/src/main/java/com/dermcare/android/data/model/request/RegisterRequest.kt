package com.dermcare.android.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("password")
	val password: String,




)
