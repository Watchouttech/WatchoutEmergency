package com.example.watchoutemergency.Dao

import com.google.gson.annotations.SerializedName

data class FindResponderSosResponse(

	@field:SerializedName("responderId")
	val responderId: String? = null,

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("userType")
	val userType: String? = null,

	@field:SerializedName("vehicleId")
	val vehicleId: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
