package com.example.loyiha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loyiha.databinding.ActivityCheckPrivacyBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CheckPrivacyActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckPrivacyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var userList = ArrayList<String>()
        val type = object : TypeToken<ArrayList<String>>(){}.type
        val gson = Gson()
        val getPreferences = getSharedPreferences("Pincode", MODE_PRIVATE)
        val str = getPreferences.getString("code", "")
        if (str ==""){

        }else{
            userList = gson.fromJson(str, type)
        }
        binding.check.setOnClickListener {
            if (binding.pinview.text!!.length==4){
                var a = 0
                for (i in 0..userList.size-1){
                    if (userList[i]==binding.pinview.text.toString()){
                        a=1
                    }
                }
                if (a==1){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Wrong password", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext, "Enter password", Toast.LENGTH_LONG).show()
            }
        }
    }
}