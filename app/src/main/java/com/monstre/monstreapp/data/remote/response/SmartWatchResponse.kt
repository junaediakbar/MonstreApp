package com.monstre.monstreapp.data.remote.response

data class SmartWatchResponse(
	val createdAt: String,
	val heartRate: Int,
	val oxiRate: Int,
	val V: Int,
	val id: String,
	val updatedAt: String
)

