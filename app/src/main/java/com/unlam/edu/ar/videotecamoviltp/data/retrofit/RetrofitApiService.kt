package com.unlam.edu.ar.videotecamoviltp.data.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.Genres
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies
import retrofit2.Callback

interface RetrofitApiService {
    fun getMovie(title: String, callback: Callback<Movies>)

    fun getGenreID(title: Int, callback: Callback<Genres>)

    fun getMovieID(id: Int, callback: Callback<MovieFav_Details_Model>)
}