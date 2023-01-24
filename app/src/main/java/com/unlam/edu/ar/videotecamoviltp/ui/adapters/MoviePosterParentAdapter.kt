package com.unlam.edu.ar.videotecamoviltp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.domain.model.MoviesByGenreModel

class MoviePosterParentAdapter(private val movieList: List<MoviesByGenreModel>) :
    RecyclerView.Adapter<MoviePosterParentAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreTitle: TextView = itemView.findViewById(R.id.tvGenreTitle)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.rvChildMoviePosters)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_home_parent_item_layout, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = movieList[position]

        holder.genreTitle.text = parentItem.genreTitle

        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(
            holder.itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val adapter = MoviePosterChildAdapter(parentItem.movieList)
        holder.childRecyclerView.adapter = adapter
    }

    override fun getItemCount(): Int = movieList.size

}