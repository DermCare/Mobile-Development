package com.dermcare.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("payload")
    val payload: UserData,
)

data class UserData(

    @field:SerializedName("id")
    val token: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("age")
    val age: String,
)