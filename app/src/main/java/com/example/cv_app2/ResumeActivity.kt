package com.example.cv_app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cv_app2.databinding.ActivityResumeBinding

class ResumeActivity : AppCompatActivity() {
    lateinit var mainView: ActivityResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        supportActionBar?.title = "Your Resume"

        setupView()
    }

    fun setupView() {
        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val androidSkill = intent.getStringExtra("androidSkill")
        val iOSSkill = intent.getStringExtra("iOSSkill")
        val flutterSkill = intent.getStringExtra("flutterSkill")
        val languages = intent.getStringArrayListExtra("languages")
        val hobbies = intent.getStringArrayListExtra("hobbies")


        mainView.lblName.text = "Name: $fullname"
        mainView.lblEmail.text = "Email: $email"
        mainView.lblAge.text = "Age: $age"
        mainView.lblGender.text = "Gender: $gender"
        mainView.lblAndroid.text = "Android Skills: $androidSkill"
        mainView.lblIOS.text = "iOs Skills: $iOSSkill"
        mainView.lblFlutter.text = "Flutter Skills: $flutterSkill"
        mainView.lblLanguages.text = "Languages: "

        if (languages != null) {
            if(languages.isEmpty()){
                mainView.lblLanguages.append("none")
            }else{
                for (lang in languages) {
                    mainView.lblLanguages.append("$lang, ")
                }
            }

        }
        mainView.lblHobbies.text = "Hobbies: "

        if (hobbies != null) {
            if(hobbies.isEmpty()){
                mainView.lblHobbies.append("none")
            }else {
                for (hob in hobbies) {
                    mainView.lblHobbies.append("$hob ")
                }
            }
        }
    }
}