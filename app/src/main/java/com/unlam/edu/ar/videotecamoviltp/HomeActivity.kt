package com.unlam.edu.ar.videotecamoviltp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityHomeBinding
import com.unlam.edu.ar.videotecamoviltp.model.Genres
import com.unlam.edu.ar.videotecamoviltp.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var movieAPI: API
    private lateinit var imagesAdapterAccion: ImagesAdapter
    private lateinit var imagesAdapterDrama: ImagesAdapter
    private lateinit var imagesAdapterTerror: ImagesAdapter
    private lateinit var imagesAdapterComedia: ImagesAdapter
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        injectDependencies()
        setupRecyclerView()
        getImagesComedia()
        getImagesAction()
        getImagesTerror()
        getImagesDrama()
        }

    private fun injectDependencies() {
        movieAPI = API()
       imagesAdapterAccion = ImagesAdapter { genre ->
            Toast.makeText(this@HomeActivity, genre.name, Toast.LENGTH_SHORT).show()  }
        imagesAdapterDrama = ImagesAdapter { genre ->
            Toast.makeText(this@HomeActivity, genre.name, Toast.LENGTH_SHORT).show()  }
        imagesAdapterTerror = ImagesAdapter { genre ->
            Toast.makeText(this@HomeActivity, genre.name, Toast.LENGTH_SHORT).show()  }
        imagesAdapterComedia = ImagesAdapter { genre ->
            Toast.makeText(this@HomeActivity, genre.name, Toast.LENGTH_SHORT).show()  }
        }

    private fun setupRecyclerView() {
        binding.recyclerview1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview1.adapter = imagesAdapterAccion
        binding.recyclerview2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview2.adapter = imagesAdapterComedia
        binding.recyclerview3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview3.adapter = imagesAdapterTerror
        binding.recyclerview4.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview4.adapter = imagesAdapterDrama
    }
    private fun getImagesAction(genre_id: Int = 28){
        genre_id.run {
            movieAPI.getImage(genre_id)
                .enqueue(object : Callback<Genres> {
                    override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                        if (response.isSuccessful) {
                            val genres = response.body()!!
                            imagesAdapterAccion.updateMovies(genres.results)
                            imagesAdapterAccion.notifyDataSetChanged()
                        } else {
                            showError()
                        }
                    }
                    override fun onFailure(call: Call<Genres>, t: Throwable) {
                        showError()
                    }
                })
        }
        }
    private fun getImagesTerror(genre_id: Int = 27){
        genre_id.run {
            movieAPI.getImage(genre_id)
                .enqueue(object : Callback<Genres> {
                    override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                        if (response.isSuccessful) {
                            val genres = response.body()!!
                            imagesAdapterTerror.updateMovies(genres.results)
                            imagesAdapterTerror.notifyDataSetChanged()
                        } else {
                            showError()
                        }
                    }
                    override fun onFailure(call: Call<Genres>, t: Throwable) {
                        showError()
                    }
                })
        }
    }
    private fun getImagesDrama(genre_id: Int = 18){
        genre_id.run {
            movieAPI.getImage(genre_id)
                .enqueue(object : Callback<Genres> {
                    override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                        if (response.isSuccessful) {
                            val genres = response.body()!!
                            imagesAdapterDrama.updateMovies(genres.results)
                            imagesAdapterDrama.notifyDataSetChanged()
                        } else {
                            showError()
                        }
                    }
                    override fun onFailure(call: Call<Genres>, t: Throwable) {
                        showError()
                    }
                })
        }
    }
    private fun getImagesComedia(genre_id: Int = 35){
        genre_id.run {
            movieAPI.getImage(genre_id)
                .enqueue(object : Callback<Genres> {
                    override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                        if (response.isSuccessful) {
                            val genres = response.body()!!
                            imagesAdapterComedia.updateMovies(genres.results)
                            imagesAdapterComedia.notifyDataSetChanged()
                        } else {
                            showError()
                        }
                    }
                    override fun onFailure(call: Call<Genres>, t: Throwable) {
                        showError()
                    }
                })
        }
    }
    private fun showError() {
        Toast.makeText(this@HomeActivity, R.string.error_movies, Toast.LENGTH_SHORT).show()
    }
}



