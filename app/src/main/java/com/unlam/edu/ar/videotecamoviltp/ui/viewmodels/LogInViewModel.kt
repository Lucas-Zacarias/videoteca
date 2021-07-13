package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.entities.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.UserEntityRepository

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