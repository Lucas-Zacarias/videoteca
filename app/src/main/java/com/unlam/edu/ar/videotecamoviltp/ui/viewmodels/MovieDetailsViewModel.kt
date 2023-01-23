package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDetailsModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MovieByIDRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    private val moviesRepository: MovieByIDRepository,
    private val favEntityRepository: FavEntityRepository
    ) :ViewModel() {

    val movieDetailsLiveData = MutableLiveData<MovieDetailsModel>()
    val errorMessage = MutableLiveData<String>()

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

    fun movieIntoFavList(movieId: Int, userId: Int): Boolean {
        return favEntityRepository.movieIdIntoFavList(movieId, userId)
    }

    fun addOrDeleteNewMovieFav(movieId: Int, userId: Int) {
        favEntityRepository.addOrDeleteNewMovieFav(movieId, userId)
    }
}