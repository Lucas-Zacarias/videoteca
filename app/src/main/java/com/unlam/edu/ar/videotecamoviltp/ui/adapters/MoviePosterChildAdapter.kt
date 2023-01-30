package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDataByGenreModel
import com.unlam.edu.ar.videotecamoviltp.ui.activities.MovieDetailsActivity
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI

class MoviePosterChildAdapter(private val movieList: List<MovieDataByGenreModel>) :
    RecyclerView.Adapter<MoviePosterChildAdapter.ChildViewHolder>() {

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_home_child_item_movie_layout, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val movie = movieList[position]

        Picasso.get()
            .load("${IMGPathAPI.IMG_API_PATH}${movie.poster}")
            .error(R.drawable.ic_movie_poster_not_found)
            .into(holder.poster)

        holder.poster.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            context.startActivity(intent)
        }
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imageViewHome)
    }
}
