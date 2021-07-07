package com.unlam.edu.ar.videotecamoviltp

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository

class UserViewModel(private val userEntityRepository: UserEntityRepository): ViewModel() {

    fun getUserNameById(userId:Int):String{
        var name = ""
        var user = userEntityRepository.getUserById(userId)
        if(user is UserEntity){
            name = user.name
        }
        return name
    }
}