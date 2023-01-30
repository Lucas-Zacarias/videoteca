package com.unlam.edu.ar.videotecamoviltp.data.repositories.retrofit

import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieListByGenreModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesByGenreRepository {

    private val serviceDiscoverMoviesByGenre: ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/discover/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    suspend fun getMovieListByGenreID(genreID:Int): Response<MovieListByGenreModel> {
        return serviceDiscoverMoviesByGenre.getMoviesByGenreID(genreID)
    }
}