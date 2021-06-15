package com.unlam.edu.ar.videotecamoviltp.model

import com.google.gson.annotations.SerializedName

data class MovieSearch(
    var id: Integer,

    @SerializedName("poster_path")
    var poster: String,

    @SerializedName("original_title")
    var title: String,

    @SerializedName("overview")
    var descripcion: String,

    @SerializedName("release_date")
    var estreno: String,

    //var genresIds: List<MovieGenre>
    @SerializedName("genre_ids")
    var genresIds: List<Int>
)

data class Movies(
    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("results")
    val results: List<MovieSearch>
)
