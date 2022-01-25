package com.example.submission1jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * from movieentities where isFavorite = 1")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovies(movies: MovieEntity)

    @Query("SELECT * from movieentities WHERE movieId = :movieId")
    fun getDetailMovies(movieId: Int): LiveData<MovieEntity>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getAllTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * from tvshowentities where isFavorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShows(tvShows: TvShowEntity)

    @Query("SELECT * from tvshowentities WHERE tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

}