package com.example.submission1jetpack.ui.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.vo.Resource

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        repository.getAllMovies(sort)

}