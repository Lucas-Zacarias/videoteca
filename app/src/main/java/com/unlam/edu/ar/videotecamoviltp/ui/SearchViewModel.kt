package com.unlam.edu.ar.videotecamoviltp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import com.unlam.edu.ar.videotecamoviltp.repositories.MovieRepository

class SearchViewModel (private val moviesRepository: MovieRepository):
    ViewModel(){
        val moviesList = MutableLiveData<Movies>()
        val errorMessage = MutableLiveData<String>()

    fun getMovie(query: String){
        moviesRepository.getMovie((query), {
            moviesList.value = it
        }, {
            errorMessage.value = it
        })
    }
}