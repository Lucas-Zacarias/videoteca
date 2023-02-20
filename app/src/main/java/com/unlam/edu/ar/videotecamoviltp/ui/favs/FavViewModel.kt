package com.unlam.edu.ar.videotecamoviltp.ui.favs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MovieByIDRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavViewModel(
    private val movieRepository: MovieByIDRepository,
    private val favEntityRepository: FavEntityRepository
) : ViewModel() {

    val moviesFavLiveData = MutableLiveData<List<MovieDetailsModel>>()

    private val errorMessage = MutableLiveData<String>()

    fun getMovieFavsByUserID(userID: Int) {
        val moviesList: MutableList<MovieDetailsModel> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {

            val movieIDList = favEntityRepository.getMovieFavIdsByUserId(userID)

            if (movieIDList.isNotEmpty()) {
                for (movieID in movieIDList) {
                    val response = movieRepository.getMovieByID(movieID)

                    if (response.isSuccessful && response.body() != null) {
                        val movieDetails = response.body()!!
                        moviesList.add(movieDetails)
                    } else {
                        val error = response.errorBody().toString()
                        errorMessage.value = error
                    }
                }
            }
            withContext(Dispatchers.Main) {
                moviesFavLiveData.value = moviesList
            }
        }
    }
}