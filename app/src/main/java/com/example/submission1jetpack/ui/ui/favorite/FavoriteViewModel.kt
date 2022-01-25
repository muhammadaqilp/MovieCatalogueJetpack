package com.example.submission1jetpack.ui.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repository.getFavoriteMovies()

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = repository.getFavoriteTvShows()

    fun setFavoriteMovie(movie: MovieEntity) {
        val newState = !movie.isFavorite
        repository.setMoviesFavorite(movie, newState)
    }

    fun setFavoriteTvShow(tv: TvShowEntity) {
        val newState = !tv.isFavorite
        repository.setTvShowFavorite(tv, newState)
    }

}