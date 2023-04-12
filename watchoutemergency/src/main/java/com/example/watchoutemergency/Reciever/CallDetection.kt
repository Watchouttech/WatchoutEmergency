package com.example.watchoutemergency.Reciever

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.watchoutemergency.AppUtils.AppPreference
import com.example.watchoutemergency.AppUtils.Constants
import com.example.watchoutemergency.Controller.WatchOutEmergency

object CallDetection {

    val TAG = "Check_Permission"
    val RECORD_REQUEST_CODE = 101
    lateinit var watchoutEmergencyService: WatchOutEmergency

    // Calling This Method in onRecieve Method of Broadcast Reciever
    // Give (contaxt , intent) 2 Argument of This Method

    @SuppressLint("LongLogTag")
    fun callDetection(context: Context?, intent: Intent?) {

        if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_OFFHOOK)
        ) {
            showToast(context!!, "Call started...")

        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_IDLE)
        ) {
//            showToast(context!!, "Call ended...")
            watchoutEmergencyService = WatchOutEmergency()
            watchoutEmergencyService.audioCallApi(
                AppPreference.getStringValue(Constants.AUTH_KEY, context!!)!!,
                AppPreference.getStringValue(Constants.RESPONDER_ID, context)!!,
                AppPreference.getStringValue(Constants.USER_ID, context)!!,
                AppPreference.getStringValue(Constants.VEHICLE_ID, context)!!,
                AppPreference.getStringValue(Constants.GROUP_ID, context)!!,
                AppPreference.getStringValue(Constants.LATITUDE, context)!!,
                AppPreference.getStringValue(Constants.LONGITUDE, context)!!,
                context
            )
        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_RINGING)
        ) {
            showToast(context!!, "Incoming call...");
        }
    }

// Using Custom Toast

    fun showToast(context: Context, message: String) {
        var toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    // Calling This Method for Self Check Permission of Main Activity

    fun setupPermissions(context: Context) {

        val permission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_PHONE_STATE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest(context)
        }
    }

    fun makeRequest(context: Context) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.READ_PHONE_STATE),
            RECORD_REQUEST_CODE
        )
    }

}