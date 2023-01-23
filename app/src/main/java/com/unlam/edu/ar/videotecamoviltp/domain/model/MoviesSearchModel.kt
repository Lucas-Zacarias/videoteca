package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesSearchModel(
    @SerializedName("results")
    val results: List<MovieSearchModel>
)