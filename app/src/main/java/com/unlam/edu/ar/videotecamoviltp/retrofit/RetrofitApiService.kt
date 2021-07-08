package com.unlam.edu.ar.videotecamoviltp.retrofit

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.MovieGenre
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import retrofit2.Callback

interface RetrofitApiService {
    fun getMovie(title: String, callback: Callback<Movies>)

    fun getGenreID(title: Int, callback: Callback<Genres>)

    fun getMovieID(id: Int, callback: Callback<MovieGenre>)
}