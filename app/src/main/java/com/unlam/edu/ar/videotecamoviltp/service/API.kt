package com.unlam.edu.ar.videotecamoviltp.service

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private val getAPI: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    private val getAPI1: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/discover/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun getMovie(title: String): Call<Movies> {
        return getAPI.getMovie(title)
    }
    fun getImage(genres: Int): Call<Genres>{
        return getAPI1.getGenreID(genres)
    }


}