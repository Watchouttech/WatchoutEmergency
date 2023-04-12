package com.example.watchoutemergency.Reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CallReciever : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        CallDetection.callDetection(context,intent)

    }
}