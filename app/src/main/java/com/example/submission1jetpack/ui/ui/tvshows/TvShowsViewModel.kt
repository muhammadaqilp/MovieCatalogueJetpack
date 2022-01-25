package com.example.submission1jetpack.ui.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.vo.Resource

class TvShowsViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> =
        repository.getAllTvShows(sort)

}