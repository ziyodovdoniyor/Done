package com.example.loyiha

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.loyiha.databinding.ActivitySplashBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        var anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        var anim = AnimationUtils.loadAnimation(this,R.anim.don)
        var userList = ArrayList<String>()
        var name:String = ""
        val type2 = object : TypeToken<String>(){}.type
        val type = object : TypeToken<ArrayList<String>>(){}.type
        val gson = Gson()
        val gson2 = Gson()
        val getPreferences2 = getSharedPreferences("Til", MODE_PRIVATE)
        val str2 = getPreferences2.getString("lang", "-2")
        val getPreferences = getSharedPreferences("Status", MODE_PRIVATE)
        val str = getPreferences.getString("status", "-1")
        splashBinding.logo.alpha = 0f

        splashBinding.logo.animate().setDuration(3000).alpha(1f).withEndAction{
            if (str =="-1"){
                val intent = Intent(this, LanguageActivity::class.java)
                startActivity(intent)
                return@withEndAction
            }else{
                userList = gson.fromJson(str, type)
            }
            name = gson2.fromJson(str2, type2)
            var a = 0
            for (i in 0..userList.size-1){
                if (userList[i]=="true"){
                    a=1
                }
            }
            if (name.isEmpty()){
                if (a==1){
                    val intent = Intent(this, LanguageActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Find(name)
                if (a==1){
                    val intent = Intent(this, CheckPrivacyActivity::class.java)
                    startActivity(intent)
                }else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

    private fun Find(s: String){
        if(s == "Uzbek"){
            val a = "uz"
            val locale = Locale(a)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }else if (s == "Russian"){
            val a = "ru"
            val locale = Locale(a)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }else{
            val locale = Locale.ENGLISH
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }

    }
}