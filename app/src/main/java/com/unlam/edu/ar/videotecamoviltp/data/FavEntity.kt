package com.unlam.edu.ar.videotecamoviltp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "favs",
    primaryKeys = ["favId","userId"],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FavEntity(
    @ColumnInfo(name = "favId")
    val idFav: Int,

    @ColumnInfo(name = "userId")
    val idUser: Int
)