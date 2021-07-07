package com.unlam.edu.ar.videotecamoviltp.retrofit

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.model.Movies
<<<<<<< HEAD:app/src/main/java/com/unlam/edu/ar/videotecamoviltp/retrofit/APIImplementation.kt
=======
import retrofit2.Call
>>>>>>> 7115ab07dc21a980918150ab166ff41472c37325:app/src/main/java/com/unlam/edu/ar/videotecamoviltp/service/API.kt
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

<<<<<<< HEAD:app/src/main/java/com/unlam/edu/ar/videotecamoviltp/retrofit/APIImplementation.kt
    override fun getGenreID(title: Int, callback: Callback<Genres>) {
        serviceDiscover.getGenreID(title).enqueue(callback)
    }
=======

>>>>>>> 7115ab07dc21a980918150ab166ff41472c37325:app/src/main/java/com/unlam/edu/ar/videotecamoviltp/service/API.kt
}