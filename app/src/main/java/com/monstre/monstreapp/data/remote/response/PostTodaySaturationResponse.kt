package com.monstre.monstreapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostTodaySaturationResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("data")
	val data: DataUser,

	@field:SerializedName("type")
	val type: String
)

data class DataUser(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("spo2")
	val spo2: String,

	@field:SerializedName("stress_number")
	val stressNumber: String,

	@field:SerializedName("bpm")
	val bpm: String,

	@field:SerializedName("desc")
	val desc: String
)
