package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MovieByIDRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavViewModel(
    private val movieRepository: MovieByIDRepository,
    private val favEntityRepository: FavEntityRepository
) : ViewModel() {

    val moviesFavLiveData = MutableLiveData<MutableList<MovieFav_Details_Model>>()
    private var moviesList: MutableList<MovieFav_Details_Model> = emptyList<MovieFav_Details_Model>().toMutableList()
    val errorMessage = MutableLiveData<String>()

    fun updateMoviesLiveData(movieIDList: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (movieId in movieIDList) {
                val response = movieRepository.getMovieByID(movieId)
                if (response.isSuccessful && response.body() != null) {
                    val movie = response.body()!!
                    moviesList.add(movie)
                    withContext(Dispatchers.Main) {
                        moviesFavLiveData.value = moviesList
                    }
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }
        }
    }

    fun getMoviesFavIDListByUserID(userId: Int): List<Int> {
        return favEntityRepository.getMovieFavIdByUserId(userId)
    }
}