package com.unlam.edu.ar.videotecamoviltp.service

import com.unlam.edu.ar.videotecamoviltp.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private val getAPI: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun getMovie(title: String): Call<Movies> {
        return getAPI.getMovie(title)
    }


}