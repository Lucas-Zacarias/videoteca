package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.RvFavChildItemMovieLayoutBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDetailsModel
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviesFavAdapter(
    private val movieList: List<MovieDetailsModel>,
    private val movieClickListener:(Int) -> Unit) :
    RecyclerView.Adapter<MovieFavViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val movieBinding =
            RvFavChildItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val movie = movieList[position]

        Picasso.get()
            .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
            .error(R.drawable.ic_movie_poster_not_found)
            .into(holder.binding.imageViewHome)

        holder.itemView.setOnClickListener {
            movieClickListener(movie.id)
        }
    }


    override fun getItemCount(): Int = movieList.size

}


class MovieFavViewHolder(val binding: RvFavChildItemMovieLayoutBinding) : RecyclerView.ViewHolder(binding.root)