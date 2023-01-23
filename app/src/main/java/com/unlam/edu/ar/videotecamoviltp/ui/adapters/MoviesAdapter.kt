package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ListItemMovieBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieSearchModel
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviesAdapter (
): RecyclerView.Adapter<MovieViewHolder>(){
    private val moviesList = mutableListOf<MovieSearchModel>()
    override fun getItemCount() = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.binding.titleTxt.text = movie.title
        holder.binding.descriptionTxt.text = movie.description
        Picasso.get()
            .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
            .placeholder(R.drawable.image_not_found_icon)
            .into(holder.binding.moviePoster)

        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            context.startActivity(intent)
        }
    }

    fun updateMovies(results: List<MovieSearchModel>?) {
        moviesList.clear()
        if (results != null) {
            moviesList.addAll(results)
        }
    }
}
class MovieViewHolder(val binding: ListItemMovieBinding ) : RecyclerView.ViewHolder(binding.root)


