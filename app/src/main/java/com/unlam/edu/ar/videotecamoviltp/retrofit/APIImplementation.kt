package com.unlam.edu.ar.videotecamoviltp.retrofit

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIImplementation : RetrofitApiService {
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



    override fun getMovie(title: String, callback: Callback<Movies>) {
        serviceSearch.getMovie(title).enqueue(callback)
    }

    override fun getGenreID(title: Int, callback: Callback<Genres>) {
        serviceDiscover.getGenreID(title).enqueue(callback)
    }
}