package com.unlam.edu.ar.videotecamoviltp.data

import androidx.lifecycle.MutableLiveData

class FavEntityRepository(private val favDAO: FavDAO) {

    fun getMovieFavIdByUserId(userId:Int):MutableLiveData<List<Int>>{
        return favDAO.getAllIdMovieFavByUserId(userId)
    }


}