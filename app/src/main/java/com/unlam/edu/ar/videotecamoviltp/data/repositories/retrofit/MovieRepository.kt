package com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.Genres
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies

interface MovieRepository {
    fun getMovie(query: String, callback: (Movies) -> Unit, onError: (String) ->Unit)

    fun getGenreID(query: Int, callback: (Genres) -> Unit, onError: (String) -> Unit)

    fun getMovieID(query: Int, callback: (MovieFav_Details_Model) -> Unit)
}