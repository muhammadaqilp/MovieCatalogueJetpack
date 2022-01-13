package com.example.submission1jetpack.ui.movies

import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.utils.DataDummy

class MoviesViewModel: ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateMovieData()

}