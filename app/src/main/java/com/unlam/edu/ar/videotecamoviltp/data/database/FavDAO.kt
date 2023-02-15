package com.unlam.edu.ar.videotecamoviltp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.unlam.edu.ar.videotecamoviltp.domain.entities.FavEntity

@Dao
interface FavDAO {

    @Query("SELECT favs.favId from favs WHERE favs.userId = :userId")
    suspend fun getAllIdMovieFavByUserId(userId:Int): List<Int>
    //busca por id del usuario todos los id de las movies que tiene como fav y los devuelve en una lista

    @Insert
    suspend fun insert(favEntity: FavEntity)

    @Delete
    suspend fun delete(favEntity: FavEntity)

}