package com.unlam.edu.ar.videotecamoviltp.data

class FavEntityRepository(private val favDAO: FavDAO) {

    fun getMovieFavIdByUserId(userId:Int): List<Int>{
        return favDAO.getAllIdMovieFavByUserId(userId)
    }

    fun movieIdIntoFavList(movieId:Int, userId: Int):Boolean{
        val moviesId = getMovieFavIdByUserId(userId)
        var result = false
        if(moviesId.contains(movieId)){
            result = true
        }
        return result
    }

    fun addOrDeleteNewMovieFav(movieId: Int, userId: Int){
        if(movieIdIntoFavList(movieId,userId)){
            favDAO.delete(FavEntity(movieId,userId))
        }else{
            favDAO.insert(FavEntity(movieId,userId))
        }
    }

}