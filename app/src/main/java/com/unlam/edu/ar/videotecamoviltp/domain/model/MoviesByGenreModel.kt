package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesByGenreModel(
    val genreTitle: String,

    @SerializedName("results")
    val movieList: List<MovieDataByGenreModel>
)