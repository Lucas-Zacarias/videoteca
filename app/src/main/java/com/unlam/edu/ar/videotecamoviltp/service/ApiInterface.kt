package com.unlam.edu.ar.videotecamoviltp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.unlam.edu.ar.videotecamoviltp.model.Movies

interface ApiInterface {
    companion object {
        const val API_KEY : String = "75baaa3bb4acacdaa5f27d938049e8ce"
        const val LANGUAGE_SPANISH : String = "language=es"
    }

    @GET("movie?api_key=${API_KEY}&query=movie&${LANGUAGE_SPANISH}")
    fun getMovie(
        @Query("query") title: String
    ): Call<Movies>

}