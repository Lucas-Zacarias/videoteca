package com.unlam.edu.ar.videotecamoviltp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var btnLogIn : Button
    private lateinit var btnSignUp : Button
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
    }

    private fun getViews() {
        email = binding.emailEditText
        password = binding.passwordEditText
        btnLogIn = binding.logInButton
        btnSignUp = binding.signUpButton
    }
    
}