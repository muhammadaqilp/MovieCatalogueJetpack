package com.example.submission1jetpack.ui.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.vo.Resource

class DetailContentViewModel(private val repository: Repository) : ViewModel() {

    val movieId = MutableLiveData<String>()
    val tvShowId = MutableLiveData<String>()

    fun setSelectedMovieId(id: String) {
        this.movieId.value = id
    }

    fun setSelectedTvShowId(id: String) {
        this.tvShowId.value = id
    }

    var movieList: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getDetailMovies(mMovieId)
        }

    var tvshowList: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getDetailTvShows(mTvShowId)
        }

    fun setFavoriteMovie() {
        val movieResource = movieList.value
        if (movieResource != null) {
            val movie = movieResource.data

            if (movie != null) {
                val newState = !movie.isFavorite
                repository.setMoviesFavorite(movie, newState)
            }
        }
    }

    fun setFavoriteTvShow() {
        val tvshowResource = tvshowList.value
        if (tvshowResource != null) {
            val tvshow = tvshowResource.data

            if (tvshow != null) {
                val newState = !tvshow.isFavorite
                repository.setTvShowFavorite(tvshow, newState)
            }
        }
    }
}