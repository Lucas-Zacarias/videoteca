package com.unlam.edu.ar.videotecamoviltp.data

import androidx.room.*
import com.unlam.edu.ar.videotecamoviltp.Classes.User


@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getAllUsers():List<User>
    //obtiene todos los usuarios de la bd y los devuelve en una lista de la clase User

    @Query("SELECT * FROM users WHERE users.id = :id")
    fun getUserByID(id:Int):User
    //devuelve el usuario de la bd que posea el id pasado como parametro

    @Query("SELECT * FROM users WHERE users.email like :email")
    fun getUserByEmail(email:String):User?
    //devuelve el usuario de la bd que posea el email pasado como parametro

    @Query("UPDATE users SET name = :newName WHERE email like :email")
    fun updateUserName(newName:String, email:String)
    //Recibe un nombre y el id del usuario que quiere cambiar su nombre, donde encuentre el id va a cambiar el nombre

    @Query("DELETE FROM users WHERE users.id = :id ")
    fun deleteUserByID(id:Int)
    //Borra de la bd un usuario segun su id

    @Query("DELETE FROM users WHERE users.email like :email")
    fun deleteUserByEmail(email:String)
    //Borra de la bd un usuario segun su email

    @Insert
    fun insert(entity: UserEntity)

    @Update
    fun update(entity: UserEntity)

    @Delete
    fun delete(entity: UserEntity)
}