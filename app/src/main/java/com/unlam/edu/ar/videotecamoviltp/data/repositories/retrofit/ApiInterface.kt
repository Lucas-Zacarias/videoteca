package com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.Genres
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        const val API_KEY : String = "75baaa3bb4acacdaa5f27d938049e8ce"
        const val LANGUAGE_SPANISH : String = "language=es"
    }

    @GET("movie?api_key=$API_KEY&query=movie&$LANGUAGE_SPANISH")
    suspend fun getMovie(@Query("query") title: String): Response<Movies>

    @GET("movie?api_key=$API_KEY&$LANGUAGE_SPANISH&with_genres=genre_id")
    suspend fun getGenreID(@Query("with_genres") id: Int): Response<Genres>

    @GET("{movie_id}?api_key=$API_KEY&$LANGUAGE_SPANISH")
    suspend fun getMovieByID(@Path("movie_id") id: Int): Response<MovieFav_Details_Model>
  }