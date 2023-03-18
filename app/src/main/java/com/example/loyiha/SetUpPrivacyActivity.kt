package com.example.loyiha

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.loyiha.databinding.ActivitySetUpPrivacyBinding
import com.google.gson.Gson

class SetUpPrivacyActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetUpPrivacyBinding
    lateinit var userList:ArrayList<String>
    lateinit var userlist2:ArrayList<String>
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetUpPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userList = getUsers()
        binding.save.setOnClickListener{
            Log.d("AAA", binding.pinview.text.toString())
            if (binding.pinview.text!!.length==4){
                val getPreferences = getSharedPreferences("Pincode", MODE_PRIVATE)
                val getPreferences2 = getSharedPreferences("Status", MODE_PRIVATE)
                val edit = getPreferences.edit()
                val edit2 = getPreferences2.edit()
                val type = object : com.google.gson.reflect.TypeToken<List<String>>() {}.type
                val gson = Gson()
                var pincode = binding.pinview.text
                val status = "true"
                val str = getPreferences.getString("code", "")
                val str1 = getPreferences2.getString("status", "")
                if (str == "") {
                    userList = ArrayList<String>()
                } else {
                    userList = gson.fromJson(str, type)
                }
                if (str1 == "") {
                    userlist2 = ArrayList<String>()
                } else {
                    userlist2 = gson.fromJson(str1, type)
                }
                userList.add(pincode.toString())
                userlist2.add(status)
                val s = gson.toJson(userList)
                val s1 = gson.toJson(userlist2)
                edit.putString("code", s).apply()
                edit2.putString("status", s1).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
    private fun getUsers(): ArrayList<String> {
        val getPreferences = getSharedPreferences("Pincode", MODE_PRIVATE)
        val type = object : com.google.gson.reflect.TypeToken<List<String>>() {}.type
        val gson = Gson()
        if (getPreferences.getString("code", null) == null) return ArrayList<String>()
        val a = getPreferences.getString("code", "")!!
        val list: ArrayList<String> = gson.fromJson(a, type)
        return list
    }

    private fun getUsers2():ArrayList<String>{
        val getPreferences = getSharedPreferences("Status", MODE_PRIVATE)
        val type = object : com.google.gson.reflect.TypeToken<List<String>>() {}.type
        val gson = Gson()
        if (getPreferences.getString("status", null) == null) return ArrayList<String>()
        val a = getPreferences.getString("status", "")!!
        val list: ArrayList<String> = gson.fromJson(a, type)
        return list
    }
}