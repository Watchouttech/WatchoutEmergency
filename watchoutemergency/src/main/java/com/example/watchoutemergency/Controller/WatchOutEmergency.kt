package com.example.watchoutemergency.Controller

import FindResponderRequest
import User
import Vehicle
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.watchoutemergency.Retrofit.ApiInterface
import com.example.watchoutemergency.AppUtils.AppPreference
import com.example.watchoutemergency.AppUtils.Constants
import com.example.watchoutemergency.AppUtils.ProgressLoading
import com.example.watchoutemergency.Dao.*
import com.example.watchoutemergency.R
import com.example.watchoutemergency.Reciever.CallDetection
import com.example.watchoutemergency.Retrofit.ApiConstant
import com.example.watchoutemergency.databinding.ActivityWatchOutEmergencyBinding
import com.example.watchoutemergencylibrary.ApiClient.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WatchOutEmergency : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityWatchOutEmergencyBinding
    lateinit var apiInterface: ApiInterface
    lateinit var apiClient: ApiClient

    lateinit var userEntity: UseDetails

    var btnVisibilty = false

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWatchOutEmergencyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.medicalBtn.setOnClickListener(this)
        binding.accidentBtn.setOnClickListener(this)
        binding.secutityBtn.setOnClickListener(this)
        binding.medicalCall.setOnClickListener(this)
        binding.medicalSos.setOnClickListener(this)
        binding.accidentCall.setOnClickListener(this)
        binding.accidentSos.setOnClickListener(this)
        binding.securityCall.setOnClickListener(this)
        supportActionBar?.hide()
        emergencyUiDetails()
        val userInfoJsonStr = (intent.getStringExtra(Constants.JSONSTR))!!


        val gson = Gson()
        try {
            if (userInfoJsonStr != null && userInfoJsonStr != "") {
                userEntity = gson.fromJson(userInfoJsonStr, UseDetails::class.java)
                AppPreference.setStringVale(
                    Constants.AUTH_KEY,
                    userEntity.apiAuthrizationKey!!,
                    this
                )
                AppPreference.setStringVale(
                    Constants.LONGITUDE,
                    userEntity.longitude!!.toString(),
                    this
                )
                AppPreference.setStringVale(
                    Constants.LATITUDE,
                    userEntity.latitude!!.toString(),
                    this
                )

            } else Toast.makeText(this, "Valid Json Required", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            userEntity = UseDetails("", "", "", "", "", null, "", null)

            Toast.makeText(this, "Valid Json Required", Toast.LENGTH_SHORT).show()
        }
        CallDetection.setupPermissions(this)
    }

    fun emergencyUiDetails() {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(ApiConstant.BASE_URL)!!.create(ApiInterface::class.java)
        ProgressLoading.show(this)
        var call: Call<ResponderListDao?>? = apiInterface.ResponderList()
        call?.enqueue(object : Callback<ResponderListDao?> {
            override fun onResponse(
                call: Call<ResponderListDao?>,
                response: Response<ResponderListDao?>
            ) {
                ProgressLoading.dismiss()
                if (response.isSuccessful && response.body() != null) {
                    binding.medicalBtn.setText(response.body()!!.data!![0].group_name)
                    binding.accidentBtn.setText(response.body()!!.data!![1].group_name)
                    binding.secutityBtn.setText(response.body()!!.data!![2].group_name)
                    AppPreference.setStringVale(
                        Constants.MEDICAL_GROUP_ID,
                        response.body()!!.data!![0]._id!!,
                        this@WatchOutEmergency
                    )
                    AppPreference.setStringVale(
                        Constants.ACCIDENT_GROUP_ID,
                        response.body()!!.data!![1]._id!!,
                        this@WatchOutEmergency
                    )
                    AppPreference.setStringVale(
                        Constants.SECURITY_GROUP_ID,
                        response.body()!!.data!![2]._id!!,
                        this@WatchOutEmergency
                    )
                } else Log.d("Error Occure-->>", "Response Not Found")
            }

            override fun onFailure(call: Call<ResponderListDao?>, t: Throwable) {
                ProgressLoading.dismiss()
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
                Toast.makeText(
                    this@WatchOutEmergency,
                    t.message!!,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    @SuppressLint("SuspiciousIndentation")
    fun medicalAccidentSecurityCallApi(CallIncomingType: String, groupId: String, calType: String) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(ApiConstant.BASE_URL)!!.create(ApiInterface::class.java)

        ProgressLoading.show(this)
        try {

            var call: Call<FindResponderResponse?>? = apiInterface.FindRespondercCall(
                userEntity.apiAuthrizationKey!!, FindResponderRequest(
                    CallIncomingType,
                    userEntity.tripBy,
                    userEntity.longitude,
                    userEntity.latitude,
                    groupId,
                    userEntity.userType,
                    User(
                        userEntity.user!!.firstName,
                        userEntity.user!!.lastName,
                        userEntity.user!!.gender,
                        userEntity.user!!.phone,
                        userEntity.user!!.email
                    ),
                    "EmergencyMessage",
                    Vehicle(
                        userEntity.vehicle!!.vehMake,
                        userEntity.vehicle!!.vehicle_number,
                        userEntity.vehicle!!.vehicle_model,
                        userEntity.vehicle!!.vehYear,
                        userEntity.vehicle!!.vinNum,
                        userEntity.vehicle!!.vehicleType

                    )
                )
            )
            call!!.enqueue(object : Callback<FindResponderResponse?> {
                @SuppressLint("LongLogTag")
                override fun onResponse(
                    call: Call<FindResponderResponse?>,
                    response: Response<FindResponderResponse?>
                ) {
                    ProgressLoading.dismiss()
                    if (response.body()!!.status.equals("success")) {
                        if (response.body()!!.responder != null && response.body()!!.responder!!.responderPhone != null) {
                            AppPreference.setStringVale(
                                Constants.RESPONDER_ID,
                                response.body()!!.responder!!.responderId!!,
                                this@WatchOutEmergency
                            )

                            AppPreference.setStringVale(
                                Constants.USER_ID,
                                response.body()!!.userId!!,
                                this@WatchOutEmergency
                            )
                            AppPreference.setStringVale(
                                Constants.VEHICLE_ID,
                                response.body()!!.vehicleId!!,
                                this@WatchOutEmergency
                            )
                            AppPreference.setStringVale(
                                Constants.GROUP_ID,
                                response.body()!!.responder!!.groupId!!,
                                this@WatchOutEmergency
                            )
                            if (calType.equals("m") || calType.equals("a")) {
                                openDialer(response.body()!!.responder!!.responderPhone!!)

                                Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                            } else {
                                openDialer(response.body()!!.responder!!.emergencyContact!!.get(0)!!)
                            }
                        } else if (calType.equals("s") && response.body()!!.responder!!.emergencyContact!!.isNotEmpty()) {
                            openDialer(response.body()!!.responder!!.emergencyContact!!.get(0)!!)
                        } else {
                            Toast.makeText(
                                this@WatchOutEmergency,
                                "Nearest Responder not Found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this@WatchOutEmergency,
                            "${response.body()!!.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

                override fun onFailure(call: Call<FindResponderResponse?>, t: Throwable) {
                    ProgressLoading.dismiss()
                    call.cancel()
                    Log.d("Error Occure-->>", t.message!!)
                    Toast.makeText(
                        this@WatchOutEmergency,
                        t.message!!,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } catch (e: Exception) {
            ProgressLoading.dismiss()
            userEntity = UseDetails("", "", "", "", "", null, "", null)

            Toast.makeText(this, "Valid Json Required", Toast.LENGTH_SHORT).show()

        }
    }

    fun sosApi(CallIncomingType: String, groupId: String) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(ApiConstant.BASE_URL)!!.create(ApiInterface::class.java)
        ProgressLoading.show(this)

        try {
            var call: Call<FindResponderSosResponse?>? = apiInterface.FindResponderSos(
                userEntity.apiAuthrizationKey!!, FindResponderRequest(
                    CallIncomingType,
                    userEntity.tripBy,
                    userEntity.longitude,
                    userEntity.latitude,
                    groupId,
                    userEntity.userType,
                    User(
                        userEntity.user!!.firstName,
                        userEntity.user!!.lastName,
                        userEntity.user!!.gender,
                        userEntity.user!!.phone,
                        userEntity.user!!.email
                    ),
                    "EmergencyMessage",
                    Vehicle(
                        userEntity.vehicle!!.vehMake,
                        userEntity.vehicle!!.vehicle_number,
                        userEntity.vehicle!!.vehicle_model,
                        userEntity.vehicle!!.vehYear,
                        userEntity.vehicle!!.vinNum,
                        userEntity.vehicle!!.vehicleType

                    )
                )
            )
            call!!.enqueue(object : Callback<FindResponderSosResponse?> {
                override fun onResponse(
                    call: Call<FindResponderSosResponse?>,
                    response: Response<FindResponderSosResponse?>
                ) {
                    ProgressLoading.dismiss()
                    Toast.makeText(
                        this@WatchOutEmergency,
                        response.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<FindResponderSosResponse?>, t: Throwable) {
                    call.cancel()
                    Log.d("Error Occure-->>", t.message!!)
                    Toast.makeText(
                        this@WatchOutEmergency,
                        t.message!!,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } catch (e: Exception) {
            ProgressLoading.dismiss()
            userEntity = UseDetails("", "", "", "", "", null, "", null)

            Toast.makeText(this, "Valid Json Required", Toast.LENGTH_SHORT).show()

        }
    }


    // this api hit after call end, for new case create
    fun audioCallApi(
        auth: String,
        responderId: String,
        userId: String,
        vehichleId: String,
        groupId: String,
        lat: String,
        lang: String,
        context: Context
    ) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(ApiConstant.BASE_URL)!!.create(ApiInterface::class.java)
        val call: Call<AudioCallResponse?>? = apiInterface.audioCallApi(
            auth,
            responderId, userId, vehichleId,
            groupId, lat, lang
        )
        call!!.enqueue(object : Callback<AudioCallResponse?> {
            override fun onResponse(
                call: Call<AudioCallResponse?>,
                response: Response<AudioCallResponse?>
            ) {
                if (response.body()!=null && response.body()!!.status.equals("success")) {
                    ProgressLoading.dismiss()

                    Toast.makeText(
                        context,
                        response.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<AudioCallResponse?>, t: Throwable) {
                call.cancel()
                Toast.makeText(this@WatchOutEmergency, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun openDialer(number: String) {
        var intent: Intent
        intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:" + number))
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.medical_btn) {
            if (!btnVisibilty) {
                binding.medicalCall.setVisibility(View.VISIBLE)
                binding.medicalSos.setVisibility(View.VISIBLE)
                binding.accidentCall.setVisibility(View.GONE)
                binding.accidentSos.setVisibility(View.GONE)
                binding.securityCall.setVisibility(View.GONE)
            } else {
                binding.medicalCall.setVisibility(View.GONE)
                binding.medicalSos.setVisibility(View.GONE)
            }
            btnVisibilty = !btnVisibilty
        }
        if (v?.id == R.id.medical_call) {
            medicalAccidentSecurityCallApi(
                Constants.AUDIO,
                AppPreference.getStringValue(Constants.MEDICAL_GROUP_ID, this)!!,
                "m"
            )

        }
        if (v?.id == R.id.medical_sos) {
            sosApi(Constants.SOS, AppPreference.getStringValue(Constants.MEDICAL_GROUP_ID, this)!!)
        }
        if (v?.id == R.id.accident_btn) {
            if (!btnVisibilty) {
                binding.accidentCall.setVisibility(View.VISIBLE)
                binding.accidentSos.setVisibility(View.VISIBLE)
                binding.medicalCall.setVisibility(View.GONE)
                binding.medicalSos.setVisibility(View.GONE)
                binding.securityCall.setVisibility(View.GONE)
            } else {
                binding.accidentCall.setVisibility(View.GONE)
                binding.accidentSos.setVisibility(View.GONE)
            }
            btnVisibilty = !btnVisibilty
        }
        if (v?.id == R.id.accident_call) {
            medicalAccidentSecurityCallApi(
                Constants.AUDIO,
                AppPreference.getStringValue(Constants.ACCIDENT_GROUP_ID, this)!!,
                "a"
            )

        }
        if (v?.id == R.id.accident_sos) {
            sosApi(Constants.SOS, AppPreference.getStringValue(Constants.ACCIDENT_GROUP_ID, this)!!)
        }
        if (v?.id == R.id.secutity_btn) {
            if (!btnVisibilty) {
                binding.securityCall.setVisibility(View.VISIBLE)
                binding.accidentCall.setVisibility(View.GONE)
                binding.accidentSos.setVisibility(View.GONE)
                binding.medicalCall.setVisibility(View.GONE)
                binding.medicalSos.setVisibility(View.GONE)
            } else {
                binding.securityCall.setVisibility(View.GONE)
            }
            btnVisibilty = !btnVisibilty
        }
        if (v?.id == R.id.security_call) {
            medicalAccidentSecurityCallApi(
                Constants.AUDIO,
                AppPreference.getStringValue(Constants.SECURITY_GROUP_ID, this)!!,
                "s"
            )
        }
    }
}

