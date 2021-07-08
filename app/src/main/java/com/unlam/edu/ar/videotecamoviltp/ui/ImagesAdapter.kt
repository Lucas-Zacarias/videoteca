package com.unlam.edu.ar.videotecamoviltp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.HomeImagesBinding
import com.unlam.edu.ar.videotecamoviltp.model.GenreID

class ImagesAdapter (
    private val clickListener : (String, String, String, String, Int, Int, Int) -> Unit
    ): RecyclerView.Adapter<ImageViewHolder>(){
        private val moviesList = mutableListOf<GenreID>()

        override fun getItemCount() = moviesList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val imageBinding = HomeImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageViewHolder(imageBinding)
        }

        companion object {
            const val IMG_API_PATH : String = "https://image.tmdb.org/t/p/w500"
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val movie = moviesList[position]
            Picasso.get()
                .load("${IMG_API_PATH}${movie.poster}")
                .into(holder.binding.imageViewAction)
            holder.binding.imageViewAction.setOnClickListener {
                clickListener(movie.title, movie.descripcion, movie.poster, movie.estreno, movie.presupuesto, movie.duracion, movie.ingresos)
            }
            }
    fun updateMovies(results: List<GenreID>?) {
            moviesList.clear()
            if (results != null) {
                moviesList.addAll(results)
            }
        }
    }
class ImageViewHolder(val binding: HomeImagesBinding ) : RecyclerView.ViewHolder(binding.root)
