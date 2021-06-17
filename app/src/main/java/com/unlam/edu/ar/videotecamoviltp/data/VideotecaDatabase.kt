package com.unlam.edu.ar.videotecamoviltp.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [UserEntity::class]
)


abstract class VideotecaDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
}