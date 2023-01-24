package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieByGenreModel(
    @SerializedName("results")
    val moviesByGenreModel: List<MovieDataByGenreModel>
    )
