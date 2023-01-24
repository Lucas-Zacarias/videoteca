package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDataByGenreModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val poster: String,

    @SerializedName("genre_ids")
    val genre: List<Int>
)
