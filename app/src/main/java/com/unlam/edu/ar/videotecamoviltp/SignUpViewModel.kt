package com.unlam.edu.ar.videotecamoviltp

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class SignUpViewModel(private val userEntityRepository: UserEntityRepository):ViewModel() {

    fun saveNewUser(newUser:UserEntity):Boolean{
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