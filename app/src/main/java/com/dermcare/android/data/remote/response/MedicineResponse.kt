package com.dermcare.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class MedicineResponse(

	@field:SerializedName("payload")
	val payload: List<MedicineItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class MedicineItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("class")
	val medicineClass: String
)
