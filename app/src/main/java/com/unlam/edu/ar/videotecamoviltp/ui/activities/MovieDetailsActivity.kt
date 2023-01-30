package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityMovieDetailsBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieGenreModel
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.MovieDetailsViewModel
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var release_date: TextView
    private lateinit var genre_list: TextView
    private lateinit var runtime: TextView
    private lateinit var presupuesto: TextView
    private lateinit var ingresos: TextView
    private lateinit var back: ImageButton
    private lateinit var fav: ImageButton
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(LayoutInflater.from(this))
        sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        setContentView(binding.root)
        getViews()
        setMovie()
        setListeners()
    }

    private fun getViews() {
        poster = binding.imgCoverFilm
        title = binding.txtTitulo
        description = binding.txtDescripcionSinopsis
        release_date = binding.txtAnio
        runtime = binding.txtDuracion
        presupuesto = binding.txtPresupuesto
        ingresos = binding.txtIngresos
        back = binding.arrowBack
        fav = binding.btnSelectFavourite
        genre_list = binding.txtGenreList
    }

    private fun setListeners() {
        back.setOnClickListener { finish() } //Funcionalidad del boton de volver
        fav.setOnClickListener{ addOrDeleteMovieOfFavList()}
    }

    private fun addOrDeleteMovieOfFavList() {
        movieDetailsViewModel.addOrDeleteNewMovieFav(getMovieId(),getUserId())
        setFavIcon()
    }


    private fun setMovie() {
        movieDetailsViewModel.getMovieDetailsById(getMovieId())
        setUpObserver()
        setFavIcon()
    }

    private fun setUpObserver() {
        movieDetailsViewModel.movieDetailsLiveData.observe(this,{movie->
            title.text = movie.title
            description.text = movie.description
            release_date.text = setReleaseDate(movie.releaseDate)
            runtime.text = setRuntimeToHoursAndMinutes(movie.runtime)
            presupuesto.text = setPresupuesto(movie.budget)
            ingresos.text = setIngresos(movie.revenue)
            genre_list.text = setGenreList(movie.genreList)

            Picasso.get()
                .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
                .error(R.drawable.ic_movie_poster_not_found)
                .into(poster)
        })
    }

    private fun setReleaseDate(estreno: String): String {
        val fechaDividida = estreno.split("-")
        return "Estreno: ${fechaDividida[2]} ${fechaDividida[1]} ${fechaDividida[0]}"
    }

    private fun setIngresos(ingresos: Long): String {
        var revenue = "Ingresos:"
        if(ingresos > 0){
            revenue+= " $${ingresos}"
        }else{
            revenue+= " No disponible"
        }
        return revenue
    }

    private fun setPresupuesto(presupuesto: Int): String {
        var budget = "Presupuesto:"
        if(presupuesto > 0){
            budget+= " $${presupuesto}"
        }else{
            budget+= " No disponible"
        }
        return budget
    }

    private fun setRuntimeToHoursAndMinutes(runtime:Int):String{
        var duracion = "Duración:"
        if(runtime==0){
            duracion += " No disponible"
        }else{
            if((runtime/60)>0){
                duracion += " ${runtime/60}h ${runtime%60}m"
            }else{
                duracion += " ${runtime%60}m"
            }
        }
        return duracion
    }

    private fun setGenreList(genreList: List<MovieGenreModel>):String{
        return genreList.joinToString(separator = ", ") { it.genreName }
    }

    private fun setFavIcon() {
        if(movieDetailsViewModel.movieIntoFavList(getMovieId(),getUserId())){
            fav.background = getDrawable(R.drawable.ic_fav_pressed)
        }else{
            fav.background = getDrawable(R.drawable.ic_fav_default)
        }
    }

    private fun getMovieId():Int{
        return intent.extras!!.getInt("movieId")
    }

    private fun getUserId():Int{
        return Preferences.getSharedPreferenceUserId(sharedPref)
    }
}