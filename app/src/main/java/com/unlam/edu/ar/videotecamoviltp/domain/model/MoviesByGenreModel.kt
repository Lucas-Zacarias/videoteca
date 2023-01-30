package com.unlam.edu.ar.videotecamoviltp.domain.model

data class MoviesByGenreModel(
    val genreTitle: String,

    val movieList: List<MovieDataByGenreModel>
)