package com.unlam.edu.ar.videotecamoviltp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.ListItemMovieBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieSearch
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity

class MoviesAdapter (
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
        holder.binding.titleTxt.text = movie.title
        holder.binding.descriptionTxt.text = movie.descripcion
        Picasso.get()
            .load("${IMG_API_PATH}${movie.poster}")
            .into(holder.binding.moviePoster)

        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            context.startActivity(intent)
        }
    }

    fun updateMovies(results: List<MovieSearch>?) {
        moviesList.clear()
        if (results != null) {
            moviesList.addAll(results)
        }
    }
}
class MovieViewHolder(val binding: ListItemMovieBinding ) : RecyclerView.ViewHolder(binding.root)


