package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MoviesByTitleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel (private val moviesRepository: MoviesByTitleRepository):
    ViewModel(){
        val moviesList = MutableLiveData<Movies>()
        val errorMessage = MutableLiveData<String>()

    fun getMovie(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getMoviesByTitle(query)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val movies = response.body()!!
                    moviesList.value = movies
                }
            } else {
                val error = response.errorBody().toString()
                errorMessage.value = error
            }
        }
    }
}