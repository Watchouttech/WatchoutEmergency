package com.example.watchoutemergency.AppUtils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.watchoutemergency.R

object ProgressLoading {

    lateinit var dialog:Dialog;

    fun show(cntx:Context)
    {
         dialog = Dialog(cntx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progressloading)


        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    @SuppressLint("SuspiciousIndentation")
    fun dismiss()
    {
        if(dialog.isShowing)
        dialog.dismiss()
    }
}