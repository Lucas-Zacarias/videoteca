package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieGenreModel(
    val id : Int,

    @SerializedName(value="name")
    val genreName: String
)