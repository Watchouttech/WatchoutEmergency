
import com.google.gson.annotations.SerializedName

data class FindResponderRequest(

	@field:SerializedName("caseIncomingType")
	val caseIncomingType: String? = null,

	@field:SerializedName("tripBy")
	val tripBy: String? = null,

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null,

	@field:SerializedName("groupId")
	val groupId: String? = null,

	@field:SerializedName("userType")
	val userType: String? = null,

	@field:SerializedName("user")
	val user: User? = null,


	@field:SerializedName("caseType")
	val caseType: String? = null,

	@field:SerializedName("vehicle")
	val vehicle: Vehicle? = null
)

data class User(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Vehicle(

	@field:SerializedName("vehMake")
	val vehMake: String? = null,

	@field:SerializedName("vehicle_number")
	val vehicleNumber: String? = null,

	@field:SerializedName("vehicle_model")
	val vehicleModel: String? = null,

	@field:SerializedName("vehYear")
	val vehYear: String? = null,

	@field:SerializedName("vinNum")
	val vinNum: String? = null,

	@field:SerializedName("vehicleType")
	val vehicleType: String? = null
)
