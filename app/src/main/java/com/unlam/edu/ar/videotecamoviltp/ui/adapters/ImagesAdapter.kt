package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.HomeImagesBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.GenreID
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity

class ImagesAdapter : RecyclerView.Adapter<ImageViewHolder>(){
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
                .load("$IMG_API_PATH${movie.poster}")
                .into(holder.binding.imageViewAction)
            holder.binding.imageViewAction.setOnClickListener {
                val context: Context = holder.itemView.context
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", movie.id)
                context.startActivity(intent)
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
