package com.unlam.edu.ar.videotecamoviltp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserEntity (
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @NonNull
    @ColumnInfo(name = "email")
    val email : String,

    @NonNull
    @ColumnInfo(name = "password")
    val password : String,

    @NonNull
    @ColumnInfo(name = "name")
    var name : String
)