package com.example.cv_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var txtFullName: EditText? = null
    private var txtEmail: EditText? = null
    private var txtAge: EditText? = null
    private var btnNext: Button? = null
    private var btnReset: Button? = null
    private var radioGroup: RadioGroup? = null
    private var seekbarAndroid: SeekBar? = null
    private var seekbarIOS: SeekBar? = null
    private var seekbarFlutter: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtFullName = findViewById(R.id.txtFullName)
        txtEmail = findViewById(R.id.txtEmail)
        txtAge = findViewById(R.id.txtAge)
        btnNext = findViewById(R.id.btnNext)
        btnReset = findViewById(R.id.btnReset)

        radioGroup = findViewById(R.id.radioGender)

        seekbarAndroid = findViewById(R.id.seekBarAndroid)
        seekbarIOS = findViewById(R.id.seekBarIOS)
        seekbarFlutter = findViewById(R.id.seekBarFlutter)


        btnReset!!.setOnClickListener{
            txtAge!!.text.clear()
            txtEmail!!.text.clear()
            txtFullName!!.text.clear()
            radioGroup!!.check(R.id.btnHomme)
            seekbarIOS!!.setProgress(0,true)
            seekbarAndroid!!.setProgress(0,true)
            seekbarFlutter!!.setProgress(0,true)
        }

        btnNext!!.setOnClickListener{
            if(txtAge!!.text.isEmpty() || txtEmail!!.text.isEmpty() || txtFullName!!.text.isEmpty()){
               val toast = Toast.makeText(baseContext,R.string.check_input,Toast.LENGTH_LONG)
                toast.show()
                /*Snackbar.make(
                    findViewById(R.id.constraintt),
                    "check your input !",
                    Snackbar.LENGTH_SHORT
                ).show()*/
            }else{
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail!!.text).matches()){
                    val toast = Toast.makeText(applicationContext,R.string.check_email,Toast.LENGTH_SHORT)
                    toast.show()
                }else{
                    val androidValue = seekbarAndroid!!.progress
                    val flutterValue = seekbarFlutter!!.progress
                    val iosValue = seekbarIOS!!.progress
                    if(androidValue + flutterValue + iosValue <= 90){
                        val toast = Toast.makeText(applicationContext,R.string.toast_message_low_scores,Toast.LENGTH_LONG)
                        toast.show()
                    }else{
                        val highestValue = maxOf(androidValue,flutterValue,iosValue)
                        if(highestValue > 80){
                            var tostText : String = "error getting highest value"
                            when(highestValue){
                                androidValue -> {
                                    tostText = "Vous êtes excellent en Android"
                                }
                                flutterValue -> {
                                    tostText = "Vous êtes excellent en Flutter"
                                }
                                iosValue -> {
                                    tostText = "Vous êtes excellent en IOS"
                                }
                            }
                            val toast = Toast.makeText(applicationContext,tostText,Toast.LENGTH_LONG)
                            toast.show()
                        }else{
                            val toast = Toast.makeText(applicationContext,R.string.toast_message_good_scores,Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }
                }
            }
        }


    }
}