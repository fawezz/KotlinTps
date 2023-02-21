package com.example.cv_app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cv_app2.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var mainView: ActivityMainBinding
    var selectedImage: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Create your Resume"

        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        mainView.btnNext.setOnClickListener {
            showNext()
        }

        mainView.logo.setOnClickListener {
            imagePicker()
        }
    }

    fun imagePicker(){
        pickImage.launch("image/*")
    }

    val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            // Load the selected image into the ImageView
            mainView.logo.setImageURI(it)
            selectedImage = true
        }
    }

    fun showNext() {
        if (selectedImage) {
            if (validateFields()) {
                val fullname = mainView.etFirstName.text.toString()
                val email = mainView.etEmail.text.toString()
                val age = mainView.etAge.text.toString()
                val gender = findViewById<RadioButton>(mainView.rgGender.checkedRadioButtonId).text
                var intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("fullname", fullname)
                intent.putExtra("email", email)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)
                startActivity(intent)
            }
        }else{
            Toast.makeText(this, "Please select an image!", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateFields(): Boolean {
        var validateFullName = validateFullName()
        var validateEmail = validateEmail()
        var validateAge = validateAge()
        return validateFullName && validateEmail && validateAge
    }

    fun displayError(layout: TextInputLayout, msg: String) {
        layout.isErrorEnabled = true
        layout.error = msg
    }

    fun validateFullName(): Boolean {
        var isvalid = false

        if (mainView.etFirstName.text.toString().isEmpty()) {
            displayError(mainView.txtFirstName, "Full Name cannot be empty!")

            isvalid = false
        } else {
            mainView.txtFirstName.isErrorEnabled = false
            isvalid = true
        }
        return isvalid
    }

    fun validateEmail(): Boolean {
        var isvalid = false
        if (mainView.etEmail.text.toString().isEmpty()) {
            displayError(mainView.txtEmail, "Email cannot be empty!")
            isvalid = false
        } else {

            if (!mainView.etEmail.text.toString().matches(Patterns.EMAIL_ADDRESS.toRegex())) {
                mainView.txtEmail.isErrorEnabled = true
                mainView.txtEmail.error = "Enter a valid email address!"
                displayError(mainView.txtEmail, "Enter a valid email address!")
                isvalid = false
            } else {
                mainView.txtEmail.isErrorEnabled = false
                isvalid = true
            }
        }
        return isvalid
    }

    fun validateAge(): Boolean {
        var isvalid = false
        if (mainView.etAge.text.toString().isEmpty()) {
            displayError(mainView.txtAge, "Age cannot be empty!")
            isvalid = false
        } else {
            mainView.txtAge.isErrorEnabled = false
            isvalid = true
        }
        return isvalid
    }
}