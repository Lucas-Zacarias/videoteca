package com.unlam.edu.ar.videotecamoviltp.model

import com.google.gson.annotations.SerializedName

data class GenreID(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var title: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("genre_ids")
    var genre: List<Int>,
    @SerializedName("overview")
    var descripcion: String,
    @SerializedName("release_date")
    var estreno: String,
    @SerializedName("runtime")
    var duracion: Int,
    @SerializedName("budget")
    var presupuesto: Int,
    @SerializedName("revenue")
    var ingresos: Int,
    @SerializedName("genres")
    var genreList: List<MovieGenre>,
)
data class Genres(
    @SerializedName("results")
    var results: List<GenreID>
)
