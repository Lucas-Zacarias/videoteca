package com.unlam.edu.ar.videotecamoviltp.data.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.Genres
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        const val API_KEY : String = "75baaa3bb4acacdaa5f27d938049e8ce"
        const val LANGUAGE_SPANISH : String = "language=es"
    }

    @GET("movie?api_key=$API_KEY&query=movie&$LANGUAGE_SPANISH")
    fun getMovie(
        @Query("query") title: String
    ): Call<Movies>

    @GET("movie?api_key=$API_KEY&$LANGUAGE_SPANISH&with_genres=genre_id")
    fun getGenreID(
        @Query("with_genres") id: Int
    ): Call<Genres>

    @GET("{movie_id}?api_key=$API_KEY&$LANGUAGE_SPANISH")
    fun getMovieID(
        @Path("movie_id") id: Int
    ): Call<MovieFav_Details_Model>
  }