package com.dermcare.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailDiseaseResponse(

	@field:SerializedName("payload")
	val detailDisease: DetailDiseaseItem,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DetailDiseaseItem(

	@field:SerializedName("image_link")
	val imageLink: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("desc")
	val desc: String
)
