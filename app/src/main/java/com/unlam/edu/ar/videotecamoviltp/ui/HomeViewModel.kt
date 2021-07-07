package com.unlam.edu.ar.videotecamoviltp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.repositories.MovieRepository

class HomeViewModel(private val moviesRepository: MovieRepository):
    ViewModel(){
        val moviesListDataAccion = MutableLiveData<Genres>()
        val moviesListDataDrama = MutableLiveData<Genres>()
        val moviesListDataTerror = MutableLiveData<Genres>()
        val moviesListDataComedia = MutableLiveData<Genres>()
        val errorMessage = MutableLiveData<String>()

    fun getMovieAction(genre_ID: Int = 28){
        moviesRepository.getGenreID(genre_ID, {
            moviesListDataAccion.value = it
        },{
            errorMessage.value = it
        })
    }
    fun getMovieDrama(genre_ID: Int = 18){
        moviesRepository.getGenreID(genre_ID, {
            moviesListDataDrama.value = it
        },{
            errorMessage.value = it
        })
    }
    fun getMovieTerror(genre_ID: Int = 27){
        moviesRepository.getGenreID(genre_ID, {
            moviesListDataTerror.value = it
        },{
            errorMessage.value = it
        })
    }
    fun getMovieComedia(genre_ID: Int = 35){
        moviesRepository.getGenreID(genre_ID, {
            moviesListDataComedia.value = it
        },{
            errorMessage.value = it
        })
    }
}