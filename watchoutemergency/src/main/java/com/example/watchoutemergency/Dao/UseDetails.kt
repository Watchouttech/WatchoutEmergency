package com.example.watchoutemergency.Dao


data class UseDetails(


    val apiAuthrizationKey: String? = null,
    val tripBy: String? = null,

    val latitude: Any? = null,

    val longitude: Any? = null,

    val userType: String? = null,

    val user: User? = null,

    val caseType: String? = null,

    val vehicle: Vehicle? = null
)

data class User(

    val firstName: String? = null,

    val lastName: String? = null,

    val gender: String? = null,

    val phone: String? = null,

    val email: String? = null
)

data class Vehicle(

    val vehMake: String? = null,

    val vehicle_number: String? = null,

    val vehicle_model: String? = null,

    val vehYear: String? = null,

    val vinNum: String? = null,

    val vehicleType: String? = null
)

