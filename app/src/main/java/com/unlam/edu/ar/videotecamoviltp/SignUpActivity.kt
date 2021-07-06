package com.unlam.edu.ar.videotecamoviltp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.unlam.edu.ar.videotecamoviltp.Classes.ListUsers
import com.unlam.edu.ar.videotecamoviltp.Classes.User
import com.unlam.edu.ar.videotecamoviltp.ui.HomeActivity

class SignUpActivity : AppCompatActivity() {

    companion object {

        const val REGEX_EMAIL : String = "^[0-9a-zA-Z._.-]+\\@[0-9a-zA-Z._.-]+\\.[0-9a-zA-Z]+\$"

    }

    lateinit var name : EditText
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var signUp : Button
    var listUsers : ListUsers = ListUsers()

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

           createUser()

        }
    }

    private fun createUser() {

        if(email.getText().toString().isNotEmpty()
                && name.getText().toString().isNotEmpty()
                        && password.getText().toString().isNotEmpty()){

                                 if(validateEmail(email)){

                                     if(listUsers.addUserToTheList(User(name = name.getText().toString().trim(),
                                                                        email = email.getText().toString().trim(),
                                                                        password = password.getText().toString() ))){
                                                         Toast.makeText(
                                                             this@SignUpActivity,
                                                             "Registración exitosa",
                                                             Toast.LENGTH_LONG
                                                         ).show()
                                                        navigateToHome()
                                                     }else{
                                                         Toast.makeText(
                                                             this@SignUpActivity,
                                                             "Usuario ya existente con email ${email.getText()}",
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
                                                        "Por favor rellene todos los campos",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
    }

    private fun validateEmail(email: EditText): Boolean {
        return email.getText().toString().trim().matches(Regex(pattern = REGEX_EMAIL))
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}