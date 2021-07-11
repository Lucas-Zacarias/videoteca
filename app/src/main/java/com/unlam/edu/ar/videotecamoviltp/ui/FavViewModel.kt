package com.unlam.edu.ar.videotecamoviltp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.repositories.MoviesRepository


class FavViewModel(
    private val moviesRepository: MoviesRepository,
    private val favEntityRepository: FavEntityRepository
) : ViewModel() {

    val moviesFavLiveData = MutableLiveData<List<MovieFav_Details_Model>>()
    private var moviesList:List<MovieFav_Details_Model> = mutableListOf()

    fun getMovieDetailsById(movieId:Int){

        moviesRepository.getMovieID(
            (movieId),
            {
                moviesList += it
                moviesFavLiveData.value = moviesList
                // addMoviesListToMutableLiveData(moviesList)
            }
        )
    }

    fun updateMoviesLiveData(movieIDList:List<Int>){
        Log.i("NRO ID", "${movieIDList.size}")
        for(movieId in movieIDList){
            getMovieDetailsById(movieId)
        }
    }

    fun getMoviesFavIDListByUserID(userId:Int):List<Int>{
        return favEntityRepository.getMovieFavIdByUserId(userId)
    }
}