package com.example.cv_app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cv_app2.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    lateinit var mainView: ActivitySecondBinding
    var languages = arrayListOf<String>()
    var hobbies = arrayListOf<String>()
    var androidSkill = ""
    var iOSSkill = ""
    var flutterSkill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        supportActionBar?.title = "Create your Resume"

        mainView.btnSubmit.setOnClickListener {
            submitData()
        }
    }

    fun submitData() {
        validateData()

        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")

        val resultIntent = Intent(this, ResumeActivity::class.java)
        resultIntent.putExtra("fullname", fullname)
        resultIntent.putExtra("email", email)
        resultIntent.putExtra("age", age)
        resultIntent.putExtra("gender", gender)
        resultIntent.putExtra("languages", languages)
        resultIntent.putExtra("hobbies", hobbies)
        resultIntent.putExtra("androidSkill", androidSkill)
        resultIntent.putExtra("iOSSkill", iOSSkill)
        resultIntent.putExtra("flutterSkill", flutterSkill)
        startActivity(resultIntent)

    }

    fun validateData() {

        languages.clear()
        hobbies.clear()

        if (mainView.cbAr.isChecked) languages.add("Arabic")
        if (mainView.cbEn.isChecked) languages.add("English")
        if (mainView.cbFr.isChecked) languages.add("French")

        if (mainView.cbMusic.isChecked) hobbies.add("Music")
        if (mainView.cbSport.isChecked) hobbies.add("Sport")
        if (mainView.cbGames.isChecked) hobbies.add("Games")

        androidSkill = mainView.sbAndroid.progress.toString().replace(".0", "")
        iOSSkill = mainView.sbiOS.progress.toString().replace(".0", "")
        flutterSkill = mainView.sbFlutter.progress.toString().replace(".0", "")


    }
}