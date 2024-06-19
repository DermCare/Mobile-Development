package com.dermcare.android.data.remote.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("payload")
	val listHistory: List<HistoryItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class HistoryItem(

	@field:SerializedName("result")
	val result: String,

	@field:SerializedName("score")
	val score: Any,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("drug")
	val drug: Drug
)

data class Drug(

	@field:SerializedName("drug_name")
	val drugName: String,

	@field:SerializedName("drug_type")
	val drugType: String,

	@field:SerializedName("drug_img")
	val drugImg: String,

	@field:SerializedName("desc")
	val desc: String
)
