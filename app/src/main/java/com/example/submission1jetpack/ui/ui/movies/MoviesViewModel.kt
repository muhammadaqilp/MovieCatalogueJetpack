package com.example.submission1jetpack.ui.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.source.Repository

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = repository.getAllMovies()

}