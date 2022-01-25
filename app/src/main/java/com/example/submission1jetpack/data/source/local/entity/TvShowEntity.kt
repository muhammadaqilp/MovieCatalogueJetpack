package com.example.submission1jetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: String,

    @ColumnInfo(name = "tvShowTitle")
    var tvShowTitle: String? = null,

    @ColumnInfo(name = "tvShowOverview")
    var tvShowOverview: String? = null,

    @ColumnInfo(name = "tvShowRelease")
    var tvShowRelease: String? = null,

    @ColumnInfo(name = "tvShowDuration")
    var tvShowDuration: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var tvShowPoster: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
