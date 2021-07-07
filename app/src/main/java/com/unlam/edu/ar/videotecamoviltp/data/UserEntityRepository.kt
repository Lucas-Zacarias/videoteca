package com.unlam.edu.ar.videotecamoviltp.data

import android.app.Application
import androidx.lifecycle.LiveData


class UserEntityRepository(private val userDAO: UserDAO) {


    fun getUserByEmail(email:String): UserEntity? {
        return userDAO?.getUserByEmail(email = email)
    }

    fun insertNewUser(newUser:UserEntity):Boolean{
        var result=false
        if(getUserByEmail(newUser.email) == null){
            userDAO?.insert(newUser)
            result=true
        }
        return result
    }

    fun deleteUser(user:UserEntity){
        if(getUserByEmail(user.email) is UserEntity){
            userDAO?.delete(user)
        }
    }

}