package com.unlam.edu.ar.videotecamoviltp.ui

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class SignUpViewModel(private val userEntityRepository: UserEntityRepository):ViewModel() {


    fun saveNewUser(newUser:UserEntity):Boolean{
       return userEntityRepository.insertNewUser(newUser)
    }
}