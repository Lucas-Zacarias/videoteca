package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MoviesByGenreRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MoviesByGenreModel
import com.unlam.edu.ar.videotecamoviltp.utils.GenresUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesByGenreRepository):
    ViewModel(){

    val moviesListByGenre = MutableLiveData<List<MoviesByGenreModel>>()
    private var moviesList: MutableList<MoviesByGenreModel> = emptyList<MoviesByGenreModel>().toMutableList()
    private val errorMessage = MutableLiveData<String>()

    fun getMoviesByGenre(){
        CoroutineScope(Dispatchers.Main).launch {
            for(movieGenre in GenresUtil.genresList){
                val response = moviesRepository.getMovieListByGenreID(movieGenre.id)
                if(response.isSuccessful && response.body() != null){
                        moviesList.add(MoviesByGenreModel(movieGenre.genreName, response.body()!!.moviesByGenreModel))

                }else{
                    errorMessage.value = response.errorBody().toString()
                }
            }
                moviesListByGenre.value = moviesList
        }
    }
}