package com.example.watchoutemergency.Retrofit


import FindResponderRequest
import com.example.watchoutemergency.AppUtils.Constants
import com.example.watchoutemergency.Dao.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiInterface {

    @GET(ApiConstant.GET_RESPONDER_TYPE)
    fun ResponderList(): Call<ResponderListDao?>?

    @POST(ApiConstant.FIND_RESPONDER)
    fun FindRespondercCall(@Header("apiAuthrizationKey") apiAuthrizationKey: String,
                      @Body request: FindResponderRequest
    ): Call<FindResponderResponse?>?

    @POST(ApiConstant.FIND_RESPONDER)
    fun FindResponderSos(@Header("apiAuthrizationKey") apiAuthrizationKey: String,
                      @Body request:FindResponderRequest): Call<FindResponderSosResponse?>?

    @FormUrlEncoded
    @POST(ApiConstant.AUDIO_CALL + "{responder_id}" + "/" + "{user_Id}")
    fun audioCallApi(
        @Header("apiAuthrizationKey") apiAuthrizationKey: String,
        @Path("responder_id") responder_id: String?,
        @Path("user_Id") userId: String?,
        @Field("vehicleId") vehicleId: String?,
        @Field("groupId") groupId: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?
    ): Call<AudioCallResponse?>?

}