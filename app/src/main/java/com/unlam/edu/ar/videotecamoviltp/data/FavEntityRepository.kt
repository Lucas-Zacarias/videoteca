package com.unlam.edu.ar.videotecamoviltp.data

class FavEntityRepository(private val favDAO: FavDAO) {

    fun getMovieFavIdByUserId(userId:Int): List<Int>{
        return favDAO.getAllIdMovieFavByUserId(userId)
    }


}