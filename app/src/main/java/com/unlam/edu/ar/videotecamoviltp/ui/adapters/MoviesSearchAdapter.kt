package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.RvSearchChildItemMovieLayoutBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieSearchModel
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviesSearchAdapter (private val moviesList: List<MovieSearchModel>):
    RecyclerView.Adapter<MovieSearchViewHolder>(){

    override fun getItemCount() = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val movieBinding = RvSearchChildItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieSearchViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        val movie = moviesList[position]

        Picasso.get()
            .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
            .placeholder(R.drawable.image_not_found_icon)
            .into(holder.binding.imageViewHome)

        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            context.startActivity(intent)
        }
    }
}
class MovieSearchViewHolder(val binding: RvSearchChildItemMovieLayoutBinding ): RecyclerView.ViewHolder(binding.root)


