package com.dermcare.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class DiseasesResponse(

	@field:SerializedName("payload")
	val listDiseases: List<DiseasesItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DiseasesItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_link")
	val imageLink: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("desc")
	val desc: String
)
