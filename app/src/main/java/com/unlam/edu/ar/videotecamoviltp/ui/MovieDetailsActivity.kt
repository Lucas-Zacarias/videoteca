package com.unlam.edu.ar.videotecamoviltp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.ImagesAdapter
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
    private lateinit var director: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListeners()

        val bundle: Intent = intent
        val poster1 = bundle.getStringExtra("poster")
        val title = bundle.getStringExtra("title")
        val descripcion = bundle.getStringExtra("descripcion")
        val estreno = bundle.getStringExtra("estreno")
        val duracion = bundle.getStringExtra("duracion")
        val presupuesto = bundle.getStringExtra("presupuesto")
        val ingresos = bundle.getStringExtra("ingresos")
        binding.txtTitulo.text = "$title"

        if (descripcion != null){
            binding.txtDescripcionSinopsis.text = "$descripcion"
        }else{
            binding.txtDescripcionSinopsis.text = "Informacion no encontrada"
        }
        if (estreno != null){
            binding.txtAnio.text = "$estreno"
        }else{
            binding.txtAnio.text = "Informacion no encontrada"
        }
        if  (duracion != null){
            binding.txtDuracion.text = "$duracion"
        }else{
            binding.txtDuracion.text = "Informacion no encontrada"
        }
        if (presupuesto != null){
            binding.txtPresupuesto.text ="$presupuesto"
        }else{
            binding.txtPresupuesto.text ="Informacion no encontrada"
        }
        if (ingresos != null){
            binding.txtIngresos.text = "$ingresos"
        }else{
            binding.txtIngresos.text = "Informacion no encontrada"
        }


        Picasso.get()
            .load("${ImagesAdapter.IMG_API_PATH}${poster1}")
            .into(binding.imgCoverFilm)
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
    }

    private fun setListeners() {
        back.setOnClickListener { finish() } //Funcionalidad del boton de volver
    }
}