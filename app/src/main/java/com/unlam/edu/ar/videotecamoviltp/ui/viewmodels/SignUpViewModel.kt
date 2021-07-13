package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.entities.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.UserEntityRepository

class SignUpViewModel(private val userEntityRepository: UserEntityRepository):ViewModel() {

    fun saveNewUser(newUser: UserEntity):Boolean{
       return userEntityRepository.insertNewUser(newUser)
    }

    fun getUserId(email:String): Int{
        var userId = 0
        var user = userEntityRepository.getUserByEmail(email)
        if(user is UserEntity){
            userId = user.id
        }
        return userId
    }
}