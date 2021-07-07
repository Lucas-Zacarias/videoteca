package com.unlam.edu.ar.videotecamoviltp.repositories

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.MovieGenre
import com.unlam.edu.ar.videotecamoviltp.model.Movies

interface MovieRepository {
    fun getMovie(query: String, callback: (Movies) -> Unit, onError: (String) ->Unit)

    fun getGenreID(query: Int, callback: (Genres) -> Unit, onError: (String) -> Unit)

    fun getMovieID(query: Int, callback: (MovieGenre) -> Unit, onError: (String) -> Unit)
}