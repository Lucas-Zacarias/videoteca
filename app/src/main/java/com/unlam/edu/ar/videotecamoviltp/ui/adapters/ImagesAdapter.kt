package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.RvHomeChildItemMovieLayoutBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieByGenreModel
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity

class ImagesAdapter : RecyclerView.Adapter<ImageViewHolder>(){
        private val moviesList = mutableListOf<MovieByGenreModel>()

        override fun getItemCount() = moviesList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val imageBinding = RvHomeChildItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageViewHolder(imageBinding)
        }

        companion object {
            const val IMG_API_PATH : String = "https://image.tmdb.org/t/p/w500"
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val movie = moviesList[position]
            Picasso.get()
                .load("$IMG_API_PATH${movie.poster}")
                .into(holder.binding.imageViewHome)
            holder.binding.imageViewHome.setOnClickListener {
                val context: Context = holder.itemView.context
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", movie.id)
                context.startActivity(intent)
            }
            }
    fun updateMovies(results: List<MovieByGenreModel>?) {
            moviesList.clear()
            if (results != null) {
                moviesList.addAll(results)
            }
        }
    }
class ImageViewHolder(val binding: RvHomeChildItemMovieLayoutBinding ) : RecyclerView.ViewHolder(binding.root)
