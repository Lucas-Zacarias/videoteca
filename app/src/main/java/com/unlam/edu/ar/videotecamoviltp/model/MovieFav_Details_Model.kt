package com.unlam.edu.ar.videotecamoviltp.model

import com.google.gson.annotations.SerializedName

data class MovieFav_Details_Model(
    var id: Integer,

    @SerializedName("poster_path")
    var poster: String,

    @SerializedName("original_title")
    var title: String,

    @SerializedName("overview")
    var descripcion: String,

    @SerializedName("release_date")
    var estreno: String,

    @SerializedName("genres")
    var genreList: List<MovieGenre>,

    @SerializedName("runtime")
    var duracion: Int,

    @SerializedName("budget")
    var presupuesto: Int,

    @SerializedName("revenue")
    var ingresos: Int
)

data class MovieGenre(
    var id : Int,
    @SerializedName(value = "name")
    var genreName: String
)
