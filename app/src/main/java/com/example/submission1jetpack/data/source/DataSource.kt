package com.example.submission1jetpack.data.source

import androidx.lifecycle.LiveData
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity

interface DataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getDetailMovies(movieId: String): LiveData<MovieEntity>

    fun getDetailTvShows(tvShowId: String): LiveData<TvShowEntity>

}