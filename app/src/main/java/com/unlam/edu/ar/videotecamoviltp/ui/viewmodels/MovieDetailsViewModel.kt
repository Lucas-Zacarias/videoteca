package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MovieByIDRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    private val moviesRepository: MovieByIDRepository,
    private val favEntityRepository: FavEntityRepository
    ) :ViewModel() {

    val movieDetailsLiveData = MutableLiveData<MovieDetailsModel>()
    val isMovieFavLiveData = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()

    fun getMovieDetailsById(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getMovieByID(movieId)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    movieDetailsLiveData.value = response.body()
                }
            } else {
                errorMessage.value = response.message()
                errorMessage.value = response.errorBody().toString()
            }
        }
    }

    fun isMovieIntoFavList(movieId: Int, userId: Int) {
        viewModelScope.launch {
            isMovieFavLiveData.value = favEntityRepository.isMovieIdIntoFavList(movieId, userId)
        }
    }

    fun addOrDeleteNewMovieFav(movieId: Int, userId: Int) {
        viewModelScope.launch{
            favEntityRepository.addOrDeleteNewMovieFav(movieId, userId)
            isMovieFavLiveData.value = favEntityRepository.isMovieIdIntoFavList(movieId, userId)
        }
    }
}