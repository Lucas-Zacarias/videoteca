package com.unlam.edu.ar.videotecamoviltp.ui

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class LogInViewModel(private val userEntityRepository: UserEntityRepository) : ViewModel() {


    fun getUser(email:String, password:String):Boolean{
        var validUser = false
        var user = userEntityRepository.getUserByEmail(email)
        if(user?.password == password){
            validUser = true
        }
        return validUser
    }
}