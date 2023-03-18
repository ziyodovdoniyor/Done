package com.example.loyiha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loyiha.databinding.ActivityLoginBinding
import com.example.loyiha.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var userList = ArrayList<User>()
        var userlist2 = ArrayList<String>()
        val type2 = object : TypeToken<ArrayList<String>>(){}.type
        val gson2 = Gson()
        val getPreferences2 = getSharedPreferences("Status", MODE_PRIVATE)
        val str2 = getPreferences2.getString("status", "")
        if (str2 ==""){

        }else{
            userlist2 = gson2.fromJson(str2, type2)
        }
        val type = object : TypeToken<ArrayList<User>>(){}.type
        val gson = Gson()
        val getPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val str = getPreferences.getString("Users", "")
        if (str ==""){

        }else{
            userList = gson.fromJson(str, type)
        }
        binding.enterLogin.setOnClickListener {
            if (binding.email.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Enter email", Toast.LENGTH_SHORT).show()
            }else{
                if (binding.password.text.isNullOrEmpty()){
                    Toast.makeText(applicationContext, "Enter password", Toast.LENGTH_SHORT).show()
                }else{
                    var a = 0
                    for (i in 0..userList.size-1){
                        if (userList[i].email==binding.email.text.toString() && userList[i].password==binding.password.text.toString()){
                            a=1
                        }
                    }
                    var b = 0
                    for (i in 0..userList.size-1){
                        if (userlist2[i]=="true"){
                            b=1
                        }
                    }
                    Log.d("HHH", b.toString())
                    if (a==1){
                        if (b==1){
                            val intent = Intent(this, CheckPrivacyActivity::class.java)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this, SetUpPrivacyActivity::class.java)
                            startActivity(intent)
                        }
                    }else{
                        Toast.makeText(applicationContext, "Wrong email or password", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.newone.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}