package com.unlam.edu.ar.videotecamoviltp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class LogInViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserEntityRepository(application)

    fun getUser(email:String, password:String):Boolean{
        var validUser = false
        var user = userRepository.getUserByEmail(email)
        if(user?.password == password){
            validUser = true
        }
        return validUser
    }
}