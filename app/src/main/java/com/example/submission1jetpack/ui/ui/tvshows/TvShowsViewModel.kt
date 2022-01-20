package com.example.submission1jetpack.ui.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.data.source.Repository

class TvShowsViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = repository.getAllTvShows()

}