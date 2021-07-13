package com.unlam.edu.ar.videotecamoviltp.data.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.Genres
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.domain.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    private val serviceSearch: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    private val serviceDiscover: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/discover/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    private val serviceMovie: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(
            ApiInterface::class.java
        )

    fun getMovie(title: String): Call<Movies> {
        return serviceSearch.getMovie(title)
    }
    fun getGenreID(genres: Int): Call<Genres> {
        return serviceDiscover.getGenreID(genres)
    }
    fun getMovieID(id: Int): Call<MovieFav_Details_Model>{
        return serviceMovie.getMovieID(id)
    }
}