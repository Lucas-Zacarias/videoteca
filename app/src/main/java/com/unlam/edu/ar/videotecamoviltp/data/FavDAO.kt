package com.unlam.edu.ar.videotecamoviltp.data

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDAO {

    @Query("SELECT favs.favId from favs WHERE favs.userId = :userId")
    fun getAllIdMovieFavByUserId(userId:Int): List<Int>
    //busca por id del usuario todos los id de las movies que tiene como fav y los devuelve en una lista

    @Insert
    fun insert(favEntity: FavEntity)

    @Delete
    fun delete(favEntity: FavEntity)

}