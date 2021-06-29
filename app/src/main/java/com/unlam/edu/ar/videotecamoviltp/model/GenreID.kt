package com.unlam.edu.ar.videotecamoviltp.model

import com.google.gson.annotations.SerializedName

data class GenreID(
    @SerializedName("id")
    var id: Integer,
    @SerializedName("name")
    var name: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("genre_ids")
    var genre: List<Int>
)
data class Genres(
    @SerializedName("results")
    var results: List<GenreID>
)
