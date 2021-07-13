package com.unlam.edu.ar.videotecamoviltp.data.repositories.database

import com.unlam.edu.ar.videotecamoviltp.data.database.UserDAO
import com.unlam.edu.ar.videotecamoviltp.domain.entities.UserEntity


class UserEntityRepository(private val userDAO: UserDAO) {


    fun getUserByEmail(email:String): UserEntity? {
        return userDAO.getUserByEmail(email = email)
    }

    fun insertNewUser(newUser: UserEntity):Boolean{
        var result=false
        if(getUserByEmail(newUser.email) !is UserEntity){
            userDAO.insert(newUser)
            result=true
        }
        return result
    }

    fun getUserById(id:Int): UserEntity? {
        return userDAO.getUserByID(id)
    }
}