package com.unlam.edu.ar.videotecamoviltp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavViewModel(
    private val moviesRepository: MoviesRepository,
    private val favEntityRepository: FavEntityRepository
) : ViewModel() {

    val moviesFavLiveData = MutableLiveData<List<MovieFav_Details_Model>>()
    private var moviesList:List<MovieFav_Details_Model> = ArrayList<MovieFav_Details_Model>()

    private fun getMovieDetailsById(movieId:Int){

        moviesRepository.getMovieID(
            (movieId),
            {
                moviesList += it
                moviesFavLiveData.value = moviesList
            }
        )
    }

    fun updateMoviesLiveData(movieIDList:List<Int>){
        viewModelScope.launch(Dispatchers.IO) {
            for (movieId in movieIDList) {
                getMovieDetailsById(movieId)
            }
        }
    }

    fun getMoviesFavIDListByUserID(userId:Int):List<Int>{
        return favEntityRepository.getMovieFavIdByUserId(userId)
    }
}