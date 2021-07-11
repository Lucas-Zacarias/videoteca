package com.unlam.edu.ar.videotecamoviltp.data

import androidx.room.*
@Dao
interface UserDAO {

    @Query("SELECT * FROM users WHERE users.id = :id")
    fun getUserByID(id:Int):UserEntity?
    //devuelve el usuario de la bd que posea el id pasado como parametro

    @Query("SELECT * FROM users WHERE users.email like :email")
    fun getUserByEmail(email:String):UserEntity?
    //devuelve el usuario de la bd que posea el email pasado como parametro

    @Insert
    fun insert(entity: UserEntity)

    @Update
    fun update(entity: UserEntity)

    @Delete
    fun delete(entity: UserEntity)
}