package com.example.watchoutemergency.AppUtils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.watchoutemergency.Dao.UseDetails
import com.google.gson.Gson
import java.util.Objects

object AppPreference {

    private var editor: SharedPreferences.Editor? = null
    private var preferences: SharedPreferences? = null

   val SharedprefernceName = "PreferenceData"

    @SuppressLint("CommitPrefEdits")
    private fun initialiseEditor(cntx: Context) {
        preferences = cntx.getSharedPreferences(SharedprefernceName, Context.MODE_PRIVATE);
        editor = preferences!!.edit()

    }

    private fun commitEditor() {
        editor!!.commit()
    }

    @SuppressLint("CommitPrefEdits")
    fun setStringVale(key: String, value: String, cntx: Context) {
        initialiseEditor(cntx)

        editor!!.putString(key, value)

        commitEditor()

    }

    @SuppressLint("CommitPrefEdits")
    fun setSBoolenVale(key: String, value: Boolean, cntx: Context) {

        editor!!.putBoolean(key, value)

        commitEditor()
    }

    fun clearPreference(cntx:Context)
    {
        initialiseEditor(cntx)

        editor!!.clear()
        editor!!.commit()

    }


    fun getStringValue(key: String, cntx: Context): String? {
        initialiseEditor(cntx)

        return preferences?.getString(key, "null")
    }


    fun getBoolenValue(key: String, cntx: Context): Boolean? {
        initialiseEditor(cntx)

        return preferences?.getBoolean(key, false)
    }


//    fun convertJsonStrToJson(cntx: Context, userInfoJsonStr:String, userEntityClass:Class<*>):Class<*> {
//        val gson = Gson()
//        try {
//            if (userInfoJsonStr != null && userInfoJsonStr != "")
//            {
//                val userEntity = gson.fromJson(userInfoJsonStr, userEntityClass::class.java)
//                return userEntity
//
//            }
//            else Toast.makeText(cntx, "Valid Json Required", Toast.LENGTH_SHORT).show()
//        }
//        catch (e: Exception) {
//
//            Toast.makeText(cntx, e.message, Toast.LENGTH_SHORT).show()
//        }
//    }
    }