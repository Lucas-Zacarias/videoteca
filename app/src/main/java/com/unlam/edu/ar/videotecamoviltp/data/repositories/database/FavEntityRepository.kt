package com.unlam.edu.ar.videotecamoviltp.data.repositories.database

import com.unlam.edu.ar.videotecamoviltp.data.database.FavDAO
import com.unlam.edu.ar.videotecamoviltp.domain.entities.FavEntity

class FavEntityRepository(private val favDAO: FavDAO) {

    suspend fun getMovieFavIdsByUserId(userId:Int): List<Int> {
        return favDAO.getAllIdMovieFavByUserId(userId)
    }

    suspend fun isMovieIdIntoFavList(movieId:Int, userId: Int):Boolean{
        return getMovieFavIdsByUserId(userId).contains(movieId)
    }

    suspend fun addOrDeleteNewMovieFav(movieId: Int, userId: Int){
        if(isMovieIdIntoFavList(movieId,userId)){
            favDAO.delete(FavEntity(movieId,userId))
        }else{
            favDAO.insert(FavEntity(movieId,userId))
        }
    }

}