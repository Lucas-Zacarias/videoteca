package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieSearchModel(
    val id: Int,

    @SerializedName("poster_path")
    val poster: String
)
