package com.unlam.edu.ar.videotecamoviltp.ui

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class LogInViewModel(private val userEntityRepository: UserEntityRepository) : ViewModel() {


    fun validateUser(email:String, password:String):Boolean{
        var validUser = false
        val user = userEntityRepository.getUserByEmail(email)
        if(user?.password == password){
            validUser = true
        }
        return validUser
    }

    fun getUserId(email:String): Int{
        var userId = 0
        val user = userEntityRepository.getUserByEmail(email)
        if(user is UserEntity){
            userId = user.id
        }
        return userId
    }
}