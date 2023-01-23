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
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviePosterAdapter : RecyclerView.Adapter<MoviePosterViewHolder>(){
        private val moviesList = mutableListOf<MovieByGenreModel>()

        override fun getItemCount() = moviesList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
            val imageBinding = RvHomeChildItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MoviePosterViewHolder(imageBinding)
        }

        override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
            val movie = moviesList[position]
            Picasso.get()
                .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
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
class MoviePosterViewHolder(val binding: RvHomeChildItemMovieLayoutBinding ) : RecyclerView.ViewHolder(binding.root)