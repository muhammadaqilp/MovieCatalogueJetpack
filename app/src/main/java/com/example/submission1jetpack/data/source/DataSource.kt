package com.example.submission1jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.vo.Resource

interface DataSource {

    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovies(movieId: String): LiveData<Resource<MovieEntity>>

    fun getDetailTvShows(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setMoviesFavorite(movies: MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}