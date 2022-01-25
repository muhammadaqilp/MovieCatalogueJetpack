package com.example.submission1jetpack.data.source.local

import androidx.paging.DataSource
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.data.source.local.room.CatalogueDao
import com.example.submission1jetpack.utils.SortUtils

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mCatalogueDao.getAllMovies(query)
    }

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> {
        val query = SortUtils.getSortedQueryTv(sort)
        return mCatalogueDao.getAllTvShows(query)
    }

    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        mCatalogueDao.getAllFavoriteMovies()

    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mCatalogueDao.getFavoriteTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun getDetailMovies(movieId: Int) = mCatalogueDao.getDetailMovies(movieId)

    fun getDetailTvShows(tvShowId: Int) = mCatalogueDao.getDetailTvShow(tvShowId)

    fun updateMovies(movie: MovieEntity) {
        mCatalogueDao.updateMovies(movie)
    }

    fun updateTvShow(tvShow: TvShowEntity) {
        mCatalogueDao.updateTvShows(tvShow)
    }

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovies(movie)
    }

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTvShows(tvShow)
    }
}