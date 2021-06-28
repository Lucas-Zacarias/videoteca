package com.unlam.edu.ar.videotecamoviltp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class SignUpViewModel(application: Application):AndroidViewModel(application) {
    private val userRepository = UserEntityRepository(application)

    fun saveNewUser(newUser:UserEntity):Boolean{
       return userRepository.insertNewUser(newUser)
    }
}