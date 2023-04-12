package com.example.watchoutemergency.Dao

import com.google.gson.annotations.SerializedName

data class FindResponderResponse(

	@field:SerializedName("responder")
	val responder: Responder? = null,

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

data class Responder(

	@field:SerializedName("responderPhone")
	val responderPhone: String? = null,

	@field:SerializedName("responderId")
	val responderId: String? = null,

	@field:SerializedName("companyId")
	val companyId: String? = null,

	@field:SerializedName("responderName")
	val responderName: String? = null,

	@field:SerializedName("locationId")
	val locationId: String? = null,

	@field:SerializedName("groupId")
	val groupId: String? = null,

	@field:SerializedName("responderCountryCode")
	val responderCountryCode: String? = null,

	@field:SerializedName("responderType")
	val responderType: String? = null,

	@field:SerializedName("emergency_contact")
	val emergencyContact: List<String?>? = null
)
