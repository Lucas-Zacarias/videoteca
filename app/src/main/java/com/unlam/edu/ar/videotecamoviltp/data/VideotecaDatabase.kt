package com.unlam.edu.ar.videotecamoviltp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 2,
    entities = [UserEntity::class]
)


abstract class VideotecaDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        private const val DATABASE_NAME = "videoteca_db"
        private var INSTANCE: VideotecaDatabase? = null

        fun getInstance(context: Context): VideotecaDatabase? {

            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VideotecaDatabase::class.java,
                        DATABASE_NAME
                )
                        .allowMainThreadQueries()
                        .build()
            }

            return INSTANCE
        }
    }
}