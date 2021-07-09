package com.unlam.edu.ar.videotecamoviltp.data



class UserEntityRepository(private val userDAO: UserDAO) {


    fun getUserByEmail(email:String): UserEntity? {
        return userDAO.getUserByEmail(email = email)
    }

    fun insertNewUser(newUser:UserEntity):Boolean{
        var result=false
        if(getUserByEmail(newUser.email) !is UserEntity){
            userDAO.insert(newUser)
            result=true
        }
        return result
    }

    fun deleteUser(user:UserEntity){
        if(getUserByEmail(user.email) is UserEntity){
            userDAO.delete(user)
        }
    }

    fun getUserById(id:Int): UserEntity? {
        return userDAO.getUserByID(id)
    }
}