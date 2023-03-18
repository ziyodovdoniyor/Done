package com.example.loyiha

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.loyiha.databinding.ActivityLanguageBinding
import java.util.*

class LanguageActivity : AppCompatActivity() {
    lateinit var binding: ActivityLanguageBinding
    private var list = arrayListOf<String>()
    lateinit var getPreferences: SharedPreferences
    lateinit var edit: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPreferences = getSharedPreferences("Til", MODE_PRIVATE)
        edit = getPreferences.edit()

        list.add("English")
        list.add("Russian")
        list.add("Uzbek")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.button.setOnClickListener {
            val str = getPreferences.getString("profil","")
            if (str=="true"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.spinner.adapter = adapter
        val str = getPreferences.getString("lang","")
        if (str == "English"){
            binding.spinner.setSelection(0)
        }else if (str == "Russian"){
            binding.spinner.setSelection(1)
        }else if (str == "Uzbek"){
            binding.spinner.setSelection(2)
        }

        binding.spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val lang = parent!!.getItemAtPosition(position).toString()
                var language: String? = null

                when (lang) {
                    "English" -> {
                        language = "English"
                        edit.putString("lang", "English").apply()
                    }
                    "Russian" -> {
                        language = "ru"
                        edit.putString("lang", "Russian").apply()
                    }
                    "Uzbek" -> {
                        language = "uz"
                        edit.putString("lang", "Uzbek").apply()
                    }
                }
                if (language != null){
                    val locale = Locale(language)
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    baseContext.resources.updateConfiguration(
                        config,
                        baseContext.resources.displayMetrics
                    )
                }
                binding.chooseLang.text = resources.getString(R.string.choose_language)
                binding.button.text = resources.getString(R.string.next)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        })
    }
}