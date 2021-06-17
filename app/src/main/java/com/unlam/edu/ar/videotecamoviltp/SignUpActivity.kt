package com.unlam.edu.ar.videotecamoviltp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.VideotecaDatabase
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    companion object {

        const val REGEX_EMAIL : String = "^[0-9a-zA-Z._.-]+\\@[0-9a-zA-Z._.-]+\\.[0-9a-zA-Z]+\$"

    }

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var signUp : Button
    lateinit var binding : ActivitySignUpBinding
    lateinit var database : VideotecaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        createDatabase()
        getViews()
        setListener()

    }

    private fun createDatabase() {
        database = Room.databaseBuilder(
                    this,
                    VideotecaDatabase::class.java,
                    "videoteca_db"
                    )
                    .allowMainThreadQueries()
                    .build()

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

        if(email.getText().toString().isNotEmpty()
                && name.getText().toString().isNotEmpty()
                        && password.getText().toString().isNotEmpty()){

                                 if(validateEmail(email)){

                                     if(database.userDAO().getUserByEmail(email.getText().toString().trim())!=null){
                                         database.userDAO()
                                                 .insert(UserEntity(email = email.getText().toString().trim(),
                                                                    password = password.getText().toString(),
                                                                    name =  name.getText().toString().trim()))
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