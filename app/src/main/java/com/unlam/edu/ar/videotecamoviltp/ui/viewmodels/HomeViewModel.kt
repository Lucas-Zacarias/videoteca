package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.model.MoviesByGenreModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MoviesByGenreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val moviesRepository: MoviesByGenreRepository):
    ViewModel(){
    val moviesListDataAccion = MutableLiveData<MoviesByGenreModel>()
    val moviesListDataDrama = MutableLiveData<MoviesByGenreModel>()
    val moviesListDataTerror = MutableLiveData<MoviesByGenreModel>()
    val moviesListDataComedia = MutableLiveData<MoviesByGenreModel>()
    val errorMessage = MutableLiveData<String>()

    fun getMovieAction(genre_ID: Int = 28) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getGenreID(genre_ID)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    moviesListDataAccion.value = response.body()
                }
            } else {
                errorMessage.value = response.errorBody().toString()
            }
        }
    }

    fun getMovieDrama(genre_ID: Int = 18) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getGenreID(genre_ID)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    moviesListDataDrama.value = response.body()
                }
            } else {
                errorMessage.value = response.errorBody().toString()
            }
        }
    }

    fun getMovieTerror(genre_ID: Int = 27) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getGenreID(genre_ID)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    moviesListDataTerror.value = response.body()
                }
            } else {
                errorMessage.value = response.errorBody().toString()
            }
        }
    }

    fun getMovieComedia(genre_ID: Int = 35) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getGenreID(genre_ID)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    moviesListDataComedia.value = response.body()
                }
            } else {
                errorMessage.value = response.errorBody().toString()
            }
        }
    }
}