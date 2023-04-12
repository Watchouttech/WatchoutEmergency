package com.example.watchoutemergencylib

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.watchoutemergency.Controller.WatchOutEmergency
import com.example.watchoutemergencylib.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    var numbers: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goforEmergency.setOnClickListener(this)
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View?) {

        if (v?.id == R.id.gofor_emergency) {

            var jsonStr="{\n" +
                    "  \"apiAuthrizationKey\": \"1221117105526941\",\n" +
                    "  \"longitude\": 75.7873,\n" +
                    "  \"latitude\": 26.9124,\n" +
                    "  \"tripBy\": \"ola\",\n" +
                    "  \"userType\": \"p\",\n" +
                    "  \"user\": {\n" +
                    "    \"firstName\": \"vicky\",\n" +
                    "    \"lastName\": \"kumar\",\n" +
                    "    \"email\": \"vicky3i@gmail.com\",\n" +
                    "    \"phone\": \"85856895885\",\n" +
                    "    \"gender\": \"Male\"\n" +
                    "    \n" +
                    "  },\n" +
                    "  \"vehicle\": {\n" +
                    "    \"vehYear\": \"2014\",\n" +
                    "    \"vehMake\": \"Ford\",\n" +
                    "    \"vinNum\": \"1FADP3J27EL153248\",\n" +
                    "    \"vehicle_model\": \"Focus\",\n" +
                    "    \"vehicle_number\": \"RJ16CH7898\",\n" +
                    "    \"vehicleType\": \"Ambulance\"\n" +
                    "  }\n" +
                    "}"


          val intent = Intent(this, WatchOutEmergency::class.java)
            intent.putExtra("userinfo",jsonStr);
            startActivity(intent)
        }

    }
}