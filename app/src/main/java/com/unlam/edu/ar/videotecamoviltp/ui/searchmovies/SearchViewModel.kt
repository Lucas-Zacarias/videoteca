package com.unlam.edu.ar.videotecamoviltp.ui.searchmovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.model.MoviesSearchModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MoviesByTitleRepository
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.PopularMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel (
    private val moviesByTitleRepository: MoviesByTitleRepository,
    private val popularMoviesRepository: PopularMoviesRepository):
    ViewModel(){

    val moviesSearchModelList = MutableLiveData<MoviesSearchModel>()
    private val errorMessage = MutableLiveData<String>()

    fun getMovie(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesByTitleRepository.getMoviesByTitle(query)

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val movies = response.body()!!
                    moviesSearchModelList.value = movies
                }
            } else {
                val error = response.errorBody().toString()
                errorMessage.value = error
            }
        }
    }

    fun getPopularMovies(){
        CoroutineScope(Dispatchers.IO).launch{
            val response = popularMoviesRepository.getPopularMovieList()

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val movies = response.body()!!
                    moviesSearchModelList.value = movies
                }
            } else {
                val error = response.errorBody().toString()
                errorMessage.value = error
            }
        }
    }
}