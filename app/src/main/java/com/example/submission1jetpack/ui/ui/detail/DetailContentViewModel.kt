package com.example.submission1jetpack.ui.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.data.source.Repository

class DetailContentViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovieId(id: String) {
        this.movieId = id
    }

    fun setSelectedTvShowId(id: String) {
        this.tvShowId = id
    }

    fun getMovie(): LiveData<MovieEntity> = repository.getDetailMovies(movieId)

    fun getTvShows(): LiveData<TvShowEntity> = repository.getDetailTvShows(tvShowId)
}