package com.example.loyiha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loyiha.databinding.ActivityCreateAccountBinding
import com.example.loyiha.models.User
import com.google.gson.Gson

class CreateAccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateAccountBinding
    lateinit var userList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userList = getUsers()
        binding.enterCreate.setOnClickListener {
            if (binding.email.text.isNullOrEmpty() || binding.name.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Fill all strokes", Toast.LENGTH_LONG).show()
            }else{
                val getPreferences = getSharedPreferences("login", MODE_PRIVATE)
                val edit = getPreferences.edit()
                val type = object : com.google.gson.reflect.TypeToken<List<User>>() {}.type
                val gson = Gson()
                val user_name = binding.name.text.toString()
                val user_email = binding.email.text.toString()
                val user_password = binding.password.text.toString()
                val str = getPreferences.getString("Users", "")
                if (str == "") {
                    userList = ArrayList<User>()
                } else {
                    userList = gson.fromJson(str, type)
                }
                userList.add(User(user_email, user_name, user_password))
                val s = gson.toJson(userList)
                edit.putString("Users", s).apply()
                val intent = Intent(this, SetUpPrivacyActivity::class.java)
                startActivity(intent)
            }
        }
        binding.backlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getUsers(): ArrayList<User> {
        val getPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val type = object : com.google.gson.reflect.TypeToken<List<User>>() {}.type
        val gson = Gson()
        if (getPreferences.getString("Users", null) == null) return ArrayList<User>()
        val a = getPreferences.getString("Users", "")!!
        val list: ArrayList<User> = gson.fromJson(a, type)
        return list
    }
}