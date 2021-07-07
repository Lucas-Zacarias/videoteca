package com.unlam.edu.ar.videotecamoviltp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityMovieDetailsBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListeners()
    }

    private fun getViews() {
        poster = binding.imgCoverFilm
        title = binding.txtTitulo
        description = binding.txtDescripcionSinopsis
        release_date = binding.txtAnio
        genre_list = binding.txtGenero
        runtime = binding.txtDuracion
        presupuesto = binding.txtPresupuesto
        ingresos = binding.txtIngresos
        back = binding.arrowBack
        fav = binding.btnSelectFavourite
    }

    private fun setListeners() {
        back.setOnClickListener { finish() } //Funcionalidad del boton de volver
    }
}