package com.unlam.edu.ar.videotecamoviltp.repositories

import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import com.unlam.edu.ar.videotecamoviltp.retrofit.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(val retrofitApiServices: RetrofitApiService) :
    MovieRepository{

    private val cache = HashMap<String, Movies>()

    override fun getMovie(query: String, callback: (Movies) -> Unit, onError: (String) -> Unit) {
        if (cache.containsKey(query)){
            callback(cache[query]!!)
            return
        }
        retrofitApiServices.getMovie(query,
        object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    cache[query] = response.body()!!
                    callback(response.body()!!)
                } else {
                    onError("No encontrada")
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                onError(t.message.toString())
            }
        })
    }

    override fun getGenreID(query: Int, callback: (Genres) -> Unit, onError: (String) -> Unit) {
        retrofitApiServices.getGenreID(query,
        object : Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if (response.isSuccessful){
                    callback(response.body()!!)
                } else{
                    onError("No encontrada")
                }
            }
            override fun onFailure(call: Call<Genres>, t: Throwable) {
                onError(t.message.toString())
            }
        })
    }

    override fun getMovieID(query: Int, callback: (MovieGenre) -> Unit, onError: (String) -> Unit) {

    }
}