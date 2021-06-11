package com.unlam.edu.ar.videotecamoviltp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.ListItemMovieBinding
import com.unlam.edu.ar.videotecamoviltp.model.MovieSearch

class MoviesAdapter (
    private val clickListener : (MovieSearch) -> Unit
): RecyclerView.Adapter<MovieViewHolder>(){
    private val moviesList = mutableListOf<MovieSearch>()

    override fun getItemCount() = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieBinding)
    }

    companion object {
        const val IMG_API_PATH : String = "https://image.tmdb.org/t/p/w500"
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.binding.tvTitle.text = movie.title
        holder.binding.tvDescription.text = movie.descripcion

        Picasso.get()
            .load("${IMG_API_PATH}${movie.poster}")
            .into(holder.binding.ivMovie)

        holder.itemView.setOnClickListener { clickListener(movie) }
    }

    fun updateMovies(results: List<MovieSearch>?) {
        moviesList.clear()
        if (results != null) {
            moviesList.addAll(results)
        }
}
}
class MovieViewHolder(val binding: ListItemMovieBinding ) : RecyclerView.ViewHolder(binding.root)


