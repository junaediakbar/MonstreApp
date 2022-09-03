package com.monstre.monstreapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class TodaySaturationResponse(

	@field:SerializedName("data")
	val data: TodaySaturationData,

	@field:SerializedName("status")
	val status: String
)

data class TodaySaturationData(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("spo2")
	val spo2: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("stress_number")
	val stressNumber: Int,

	@field:SerializedName("bpm")
	val bpm: Int,

	@field:SerializedName("desc")
	val desc: String
)
