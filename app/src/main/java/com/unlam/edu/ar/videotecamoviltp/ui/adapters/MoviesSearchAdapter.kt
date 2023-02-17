package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.RvSearchChildItemMovieLayoutBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieSearchModel
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviesSearchAdapter(
    private val moviesList: List<MovieSearchModel>,
    private val movieClickListener:(Int) -> Unit):
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
            .error(R.drawable.ic_movie_poster_not_found)
            .into(holder.binding.imageViewHome)

        holder.itemView.setOnClickListener {
            movieClickListener(movie.id)
        }
    }
}
class MovieSearchViewHolder(val binding: RvSearchChildItemMovieLayoutBinding ): RecyclerView.ViewHolder(binding.root)


