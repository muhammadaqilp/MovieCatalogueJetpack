package com.example.submission1jetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "movieTitle")
    var movieTitle: String? = null,

    @ColumnInfo(name = "movieOverview")
    var movieOverview: String? = null,

    @ColumnInfo(name = "movieRelease")
    var movieRelease: String? = null,

    @ColumnInfo(name = "movieDuration")
    var movieDuration: String? = null,

    @ColumnInfo(name = "moviePoster")
    var moviePoster: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
