package com.unlam.edu.ar.videotecamoviltp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.databinding.ListItemMovieBinding
import com.unlam.edu.ar.videotecamoviltp.model.MovieFav_Details_Model

class MoviesFavAdapter() : RecyclerView.Adapter<MovieFavViewHolder>() {

    private val movieList = mutableListOf<MovieFav_Details_Model>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val movieBinding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        holder.bindMovieItem(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}

class MovieFavViewHolder(private val binding: ListItemMovieBinding):RecyclerView.ViewHolder(binding.root) {
    companion object {
        const val IMG_API_PATH: String = "https://image.tmdb.org/t/p/w500"
    }


    fun bindMovieItem(movie : MovieFav_Details_Model) {
        binding.titleTxt.text = movie.title
        binding.descriptionTxt.text = movie.descripcion
        binding.genres.text = movie.genreList.joinToString(separator = ", ") { it.genreName }

        Picasso.get()
            .load("${IMG_API_PATH}${movie.poster}")
            .placeholder(R.drawable.image_not_found)
            .into(binding.moviePoster)

        itemView.setOnClickListener{
            navigateToMovieDetails(movie.id)
        }
    }

    private fun navigateToMovieDetails(id: Integer) {
        val context:Context = itemView.context
        val intent = Intent(context, MovieDetailsActivity::class.java)
        context.startActivity(intent)
    }

}
