package com.unlam.edu.ar.videotecamoviltp

import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.data.FavEntityRepository
import com.unlam.edu.ar.videotecamoviltp.data.UserEntityRepository


class FavViewModel(
    private val userEntityRepository: UserEntityRepository,
    private val favEntityRepository: FavEntityRepository
) : ViewModel() {



}