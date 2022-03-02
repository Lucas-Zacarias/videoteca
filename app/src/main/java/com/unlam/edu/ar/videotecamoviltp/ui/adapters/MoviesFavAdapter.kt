package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ListItemMovieBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieFav_Details_Model
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity

class MoviesFavAdapter(private val movieList: List<MovieFav_Details_Model>) :
    RecyclerView.Adapter<MovieFavViewHolder>() {

    companion object {
        const val IMG_API: String = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val movieBinding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.titleTxt.text = movie.title
        holder.binding.descriptionTxt.text = movie.descripcion

        Picasso.get()
            .load("$IMG_API${movie.poster}")
            .placeholder(R.drawable.image_not_found_icon)
            .into(holder.binding.moviePoster)

        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return movieList.size
    }

}


class MovieFavViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root)