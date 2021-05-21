package com.unlam.edu.ar.videotecamoviltp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.unlam.edu.ar.videotecamoviltp.Classes.ListUsers
import com.unlam.edu.ar.videotecamoviltp.Classes.User

class SignUpActivity : AppCompatActivity() {

    companion object {

        const val REGEX_EMAIL : String = "^[\\\\w!#\$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#\$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}\$"

    }

    lateinit var name : EditText
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var signUp : Button
    lateinit var listUsers : ListUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        getViews()
        setListener()

    }



    private fun getViews() {
        name = findViewById(R.id.nameEditTxt)
        email = findViewById(R.id.emailEditTxt)
        password = findViewById(R.id.passEditTxt)
        signUp = findViewById(R.id.signUpBtn)
    }

   private fun setListener() {
        signUp.setOnClickListener {

            navigateToHome()
          //  createUser()
        }
    }

    private fun createUser() {
        if(email.toString().isNotEmpty()
            && name.toString().isNotEmpty() &&
            password.toString().isNotEmpty()){

            if(validateEmail(email)){

             if(listUsers.addUserToTheList(User(name = name.toString(),
                                                email = email.toString(),
                                                password = password.toString() ))){
                 Toast.makeText(
                     this@SignUpActivity,
                     "Registración exitosa",
                     Toast.LENGTH_LONG
                 ).show()
                 navigateToHome()
             }else{
                 Toast.makeText(
                     this@SignUpActivity,
                     "Usuario ya existente con email ${email}",
                     Toast.LENGTH_LONG
                 ).show()
             }
            }else{
                Toast.makeText(
                    this@SignUpActivity,
                    "Formato de email incorrecto, pruebe con uno estilo juanperez@gmail.com",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            Toast.makeText(
                this@SignUpActivity,
                "Por favor rellene los campos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun validateEmail(email: EditText): Boolean {
            val regex = Regex(pattern = REGEX_EMAIL)
            val matched = regex.containsMatchIn(email.toString().trim())
            return matched
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}