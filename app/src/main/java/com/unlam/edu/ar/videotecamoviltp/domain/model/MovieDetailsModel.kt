package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    val id: Int,

    @SerializedName("poster_path")
    val poster: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("genres")
    val genreList: List<MovieGenreModel>,

    @SerializedName("runtime")
    val runtime: Int
)
