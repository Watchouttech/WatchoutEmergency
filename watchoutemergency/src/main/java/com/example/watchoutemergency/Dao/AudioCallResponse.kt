package com.example.watchoutemergency.Dao

import com.google.gson.annotations.SerializedName

data class AudioCallResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("sender_vehicle_number")
	val senderVehicleNumber: String? = null,

	@field:SerializedName("sender_type")
	val senderType: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("sender_group")
	val senderGroup: String? = null,

	@field:SerializedName("sender_name")
	val senderName: String? = null,

	@field:SerializedName("noty_type")
	val notyType: String? = null,

	@field:SerializedName("sender_mobile")
	val senderMobile: String? = null,

	@field:SerializedName("vehicle_id")
	val vehicleId: String? = null,

	@field:SerializedName("sender_id")
	val senderId: String? = null,

	@field:SerializedName("sender_email")
	val senderEmail: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
