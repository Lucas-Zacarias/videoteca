package com.unlam.edu.ar.videotecamoviltp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.unlam.edu.ar.videotecamoviltp.ui.HomeActivity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySignUpBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {

    companion object {

        const val REGEX_EMAIL: String = "^[0-9a-zA-Z._.-]+\\@[0-9a-zA-Z._.-]+\\.[0-9a-zA-Z]+\$"

    }

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: Button
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListener()
    }


    private fun getViews() {
        name = binding.nameEditTxt
        email = binding.emailEditTxt
        password = binding.passEditTxt
        signUp = binding.signUpBtn
    }

    private fun setListener() {
        signUp.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {

        if (email.text.toString().isNotEmpty()
                && name.text.toString().isNotEmpty()
                && password.text.toString().isNotEmpty()) {

            if (validateEmail(email)) {

                if (signUpViewModel.saveNewUser(UserEntity(email = email.text.toString().trim(),
                                password = password.text.toString().trim(),
                                name = name.text.toString().trim()))){
                    Toast.makeText(
                            this@SignUpActivity,
                            "Registración exitosa",
                            Toast.LENGTH_LONG
                    ).show()
                    saveSignUpSharedPreferences(signUpViewModel.getUserId(email.text.toString().trim()))
                    navigateToHome()
                } else {
                    Toast.makeText(
                            this@SignUpActivity,
                            "Usuario ya existente con email ${email.text}",
                            Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                        this@SignUpActivity,
                        "Formato de email incorrecto, pruebe con uno estilo juanperez@gmail.com",
                        Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                    this@SignUpActivity,
                    "Por favor rellene todos los campos",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun validateEmail(email: EditText): Boolean {
        return email.text.toString().trim().matches(Regex(pattern = REGEX_EMAIL))
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun saveSignUpSharedPreferences(userId: Int) {
        val sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId",userId)
        editor.apply()
    }
    //guarda en el shared preferences el id del usuario al hacer el sign up
}