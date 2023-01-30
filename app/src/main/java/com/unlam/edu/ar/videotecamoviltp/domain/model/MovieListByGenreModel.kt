package com.unlam.edu.ar.videotecamoviltp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieListByGenreModel(
    @SerializedName("results")
    val moviesByGenreModel: List<MovieDataByGenreModel>
    )
