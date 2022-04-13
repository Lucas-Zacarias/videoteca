package com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieByIDRepository {

    private val serviceGetMovieByID: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    suspend fun getMovieByID(movieID: Int): Response<MovieFav_Details_Model> {
        return serviceGetMovieByID.getMovieByID(movieID)
    }

}