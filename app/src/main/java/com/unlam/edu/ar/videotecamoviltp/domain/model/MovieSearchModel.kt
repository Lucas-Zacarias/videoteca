package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieSearchModel(
    val id: Int,

    @SerializedName("poster_path")
    val poster: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("genre_ids")
    val genresIds: List<Int>,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("budget")
    val budget: Int
)
