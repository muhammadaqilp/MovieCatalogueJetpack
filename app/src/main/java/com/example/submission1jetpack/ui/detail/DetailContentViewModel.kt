package com.example.submission1jetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.utils.DataDummy

class DetailContentViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovieId(id: String) {
        this.movieId = id
    }

    fun setSelectedTvShowId(id: String){
        this.tvShowId = id
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateMovieData()
        for (movieEntitity in movieEntities) {
            if (movieEntitity.movieId == movieId) {
                movie = movieEntitity
            }
        }
        return movie
    }

    fun getTvShows(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = DataDummy.generateTvShowsData()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}